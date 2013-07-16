package com.sdhery.module.core.dao;

import com.sdhery.module.core.commons.Condition;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-6
 * Time: 下午3:14
 * To change this template use File | Settings | File Templates.
 */
public interface EntityDao <E, PK extends Serializable> {
    E getById(PK id);

    int deleteById(PK id);

    int insert(E e);

    int update(E e);

    List<E> search(Condition condition);

    int count(Condition condition);
}
