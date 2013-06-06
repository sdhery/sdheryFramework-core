package com.sdhery.module.core.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-6
 * Time: 下午3:13
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseEntity implements Serializable, Cloneable {
    protected static final String DATE_FORMAT = "yyyy-MM-dd";
    protected static final String TIME_FORMAT = "HH:mm:ss";
    protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    protected static final String COMPANT_TIMESTAMP_MM_FORMAT = "yy-MM-dd HH:mm";

    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
