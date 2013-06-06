package com.sdhery.module.core.service.impl;

import com.sdhery.module.core.dao.EntityDao;
import com.sdhery.module.core.service.IBaseService;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-8
 * Time: 上午11:42
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseService<E, PK extends Serializable> implements IBaseService<E, PK> {
    protected abstract EntityDao<E, PK> getEntityDao();

    public E getById(PK id) {
        return getEntityDao().getById(id);
    }

    public int deleteById(PK id) {
        return getEntityDao().deleteById(id);
    }

    public int insert(E e) {
        return getEntityDao().insert(e);
    }

    public int update(E e) {
        return getEntityDao().update(e);
    }


}
