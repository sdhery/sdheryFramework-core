package com.sdhery.core.myBatisDialect;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-6
 * Time: 下午3:49
 * To change this template use File | Settings | File Templates.
 */
public interface IDialect {
    /**
     * 生成具有limit后的简单分页SQL
     *
     * @param sql
     * @param offset    开始
     * @param limit     结束
     * @return
     */
    public String getLimitString(String sql, int offset, int limit);
}
