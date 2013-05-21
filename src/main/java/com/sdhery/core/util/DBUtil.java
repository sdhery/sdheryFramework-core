package com.sdhery.core.util;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-6
 * Time: 下午3:53
 * To change this template use File | Settings | File Templates.
 */
public class DBUtil {
    public static String getMySqlLimitString(String sql, int begin, int number) {
        StringBuilder pagingSelect = new StringBuilder(100);
        pagingSelect.append(sql);
        if (begin != -1 && number != -1) {
            pagingSelect.append(" limit " + begin + ", " + number);
        }
        return pagingSelect.toString();
    }
}
