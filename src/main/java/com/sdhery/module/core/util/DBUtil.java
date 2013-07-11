package com.sdhery.module.core.util;

import com.sdhery.module.helper.SpringContextHolder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-6
 * Time: 下午3:53
 * To change this template use File | Settings | File Templates.
 */
public class DBUtil {
    static ConcurrentHashMap Ids = null;

    /**
     * 关闭数据库操作对象
     *
     * @param conn  Connection
     * @param pstmt PreparedStatement
     * @param rs    ResultSet
     */
    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {

        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (Exception e) {

        }
        try {
            if (conn != null) {
                if (conn.isClosed() == false) {
                    conn.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMySqlLimitString(String sql, int begin, int number) {
        StringBuilder pagingSelect = new StringBuilder(100);
        pagingSelect.append(sql);
        if (begin != -1 && number != -1) {
            pagingSelect.append(" limit " + begin + ", " + number);
        }
        return pagingSelect.toString();
    }

    synchronized public static long getId(String tableName) throws Exception {
        long resultId = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isOK = false;//开始编程式事务编码
        PlatformTransactionManager transactionManager = (PlatformTransactionManager) SpringContextHolder.getBean("idtransactionManager");
        DefaultTransactionDefinition dtf = new DefaultTransactionDefinition();
        dtf.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus ts = transactionManager.getTransaction(dtf);
        try {

            if (Ids == null) {
                Ids = new ConcurrentHashMap();
            }
            IdPair id = (IdPair) Ids.get(tableName);
            if (id == null) {
                id = new IdPair();
                Ids.put(tableName, id);
            }

            if (id.curVal < id.maxVal) {
                resultId = id.curVal++;
                return resultId;
            }
            DataSource ds = (DataSource) SpringContextHolder.getBean("dataSource");
            conn = ds.getConnection();

            stmt = conn.prepareStatement("Lock tables t_ids write");
            stmt.execute();
            stmt.close();

            stmt = conn.prepareStatement("select count(*) as c from t_ids where TableName=? ");
            stmt.setString(1, tableName);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int c = rs.getInt(1);
                rs.close();
                stmt.close();
                if (c == 0) {
                    stmt = conn.prepareStatement("Insert into t_ids(TableName,NextValue)values(?,?)");
                    stmt.setString(1, tableName);
                    stmt.setInt(2, 10000);
                    stmt.execute();
                    stmt.close();
                }
            } else {
                rs.close();
                stmt.close();
            }
            stmt = conn.prepareStatement("select NextValue from t_ids where TableName=?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, tableName);
            rs = stmt.executeQuery();
            rs.next();
            int ID = rs.getInt("NextValue");

            rs.close();
            stmt.close();

            stmt = conn.prepareStatement("Update t_ids set NextValue=NextValue+100 where TableName=?");
            stmt.setString(1, tableName);
            stmt.execute();
            stmt.close();

            stmt = conn.prepareStatement("Unlock tables");
            stmt.execute();

            DBUtil.close(null, stmt, null);

            isOK = true;
            id.curVal = ID;
            id.maxVal = ID + 100;
            resultId = id.curVal++;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (isOK) {//提交事务
                transactionManager.commit(ts);
            } else {//回滚事务
                transactionManager.rollback(ts);
            }
            if (conn != null && conn.isClosed() == false) {
                conn.close();
            }
        }
        return resultId;
    }
}

class IdPair {
    public IdPair() {
        curVal = 0;
        maxVal = 0;
    }

    public long curVal;
    public long maxVal;
}
