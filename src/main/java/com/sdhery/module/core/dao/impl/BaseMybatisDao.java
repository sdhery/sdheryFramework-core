package com.sdhery.module.core.dao.impl;


import com.sdhery.module.core.commons.Condition;
import com.sdhery.module.core.dao.EntityDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-6
 * Time: 下午3:22
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseMybatisDao<E, PK extends Serializable> extends SqlSessionDaoSupport implements EntityDao<E, PK> {
    protected final Log log = LogFactory.getLog(getClass());
    private Class<E> entityClass;

    public BaseMybatisDao() {
        Class typeCls = getClass();
        Type genType = typeCls.getGenericSuperclass();

        while (!(genType instanceof ParameterizedType)) {
            typeCls = typeCls.getSuperclass();
            genType = typeCls.getGenericSuperclass();
        }
        this.entityClass = ((Class) ((ParameterizedType) genType).getActualTypeArguments()[0]);
    }

    public E getById(PK pk) {
        Object object = getSqlSession().selectOne(getByIdNameSpace(), pk);
        return (E) object;
    }

    public int deleteById(PK primaryKey) {
        return getSqlSession().delete(getByIdNameSpace(), primaryKey);
    }

    public int insert(E object) {
        return getSqlSession().insert(insertNameSpace(), object);
    }

    public int update(E object) {
        return getSqlSession().insert(updateNameSpace(), object);
    }

    public List<E> search(Condition condition) {
        return getSqlSession().selectList(searchNameSpace(), condition);
    }

    public int count(Condition condition) {
        return (Integer)getSqlSession().selectOne(searchNameSpace(), condition);
    }

    public String getByIdNameSpace() {
        return getClass().getName() + ".getById";
    }

    public String deleteByIdNameSpace() {
        return getClass().getName() + ".deleteById";
    }

    public String insertNameSpace() {
        return getClass().getName() + ".insert";
    }

    public String updateNameSpace() {
        return getClass().getName() + ".update";
    }

    public String searchNameSpace() {
        return getClass().getName() + ".search";
    }

    public String countNameSpace() {
        return getClass().getName() + ".count";
    }

}
