package com.sdhery.module.core.dataBase;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-7-12
 * Time: 上午11:21
 * To change this template use File | Settings | File Templates.
 */
public interface IIDGenerator {
    int getId(String tableName) throws Exception;
}
