package com.sdhery.core.service;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-8
 * Time: 上午11:41
 * To change this template use File | Settings | File Templates.
 */
public interface IBaseService<E, PK extends Serializable> {
    public E getById(PK id);

    public int deleteById(PK id);

    public int insert(E e);

    public int update(E e);
}
