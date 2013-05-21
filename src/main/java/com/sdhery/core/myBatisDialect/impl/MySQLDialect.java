package com.sdhery.core.myBatisDialect.impl;

import com.sdhery.core.myBatisDialect.IDialect;
import com.sdhery.core.util.DBUtil;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-6
 * Time: 下午3:52
 * To change this template use File | Settings | File Templates.
 */
public class MySQLDialect implements IDialect {
    public String getLimitString(String sql, int offset, int limit) {
        return DBUtil.getMySqlLimitString(sql, offset, limit);
    }
}
