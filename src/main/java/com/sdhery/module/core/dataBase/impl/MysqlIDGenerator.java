package com.sdhery.module.core.dataBase.impl;

import com.sdhery.module.core.dataBase.DBUtil;
import com.sdhery.module.core.dataBase.IIDGenerator;
import com.sdhery.module.core.util.spring.SpringContextHolder;
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
 * Date: 13-7-12
 * Time: 上午11:22
 * To change this template use File | Settings | File Templates.
 */
public class MysqlIDGenerator implements IIDGenerator {
    DataSource dataSource;
    PlatformTransactionManager transactionManager;
    static ConcurrentHashMap Ids = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public synchronized int getId(String tableName) throws Exception{
        int resultId = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isOK = false;
        //开始编程式事务编码
        PlatformTransactionManager platformTransactionManager = this.transactionManager;
        DefaultTransactionDefinition dtf = new DefaultTransactionDefinition();
        dtf.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus ts = platformTransactionManager.getTransaction(dtf);
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
            conn = dataSource.getConnection();

            stmt = conn.prepareStatement("Lock tables sys_ids write");
            stmt.execute();
            stmt.close();

            stmt = conn.prepareStatement("select count(*) as c from sys_ids where TABLE_NAME=? ");
            stmt.setString(1, tableName);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int c = rs.getInt(1);
                rs.close();
                stmt.close();
                if (c == 0) {
                    stmt = conn.prepareStatement("Insert into sys_ids(TABLE_NAME,NEXT_VALUE)values(?,?)");
                    stmt.setString(1, tableName);
                    stmt.setInt(2, 10000);
                    stmt.execute();
                    stmt.close();
                }
            } else {
                rs.close();
                stmt.close();
            }
            stmt = conn.prepareStatement("select NEXT_VALUE from sys_ids where TABLE_NAME=?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, tableName);
            rs = stmt.executeQuery();
            rs.next();
            int ID = rs.getInt("NEXT_VALUE");

            rs.close();
            stmt.close();

            stmt = conn.prepareStatement("Update sys_ids set NEXT_VALUE=NEXT_VALUE+100 where TABLE_NAME=?");
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
                platformTransactionManager.commit(ts);
            } else {//回滚事务
                platformTransactionManager.rollback(ts);
            }
            DBUtil.close(conn, null, null);
        }
        return resultId;
    }
}

class IdPair {
    public int curVal;
    public int maxVal;

    public IdPair() {
        this.curVal = 0;
        this.maxVal = 0;
    }
}
