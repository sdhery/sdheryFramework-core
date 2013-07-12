package com.sdhery.module.core.dataBase;

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
 * Date: 13-5-6
 * Time: 下午3:53
 * To change this template use File | Settings | File Templates.
 */
public class DBUtil {
    /**
     * 关闭数据库操作对象
     *
     * @param conn              Connection
     * @param preparedStatement PreparedStatement
     * @param rs                ResultSet
     */
    public static void close(Connection conn, PreparedStatement preparedStatement, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {

        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
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
}
