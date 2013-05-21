package com.sdhery.core.service.impl;

import com.sdhery.core.dao.EntityDao;
import com.sdhery.core.dao.impl.BaseMybatisDao;
import com.sdhery.core.service.IBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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
