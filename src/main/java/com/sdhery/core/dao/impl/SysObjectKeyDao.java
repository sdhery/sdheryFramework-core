package com.sdhery.core.dao.impl;

import com.sdhery.core.dao.ISysObjectKeyDao;
import com.sdhery.core.domain.SysObjectKey;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-24
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */
@Repository("sysObjectKeyDao")
public class SysObjectKeyDao extends BaseMybatisDao<SysObjectKey,String> implements ISysObjectKeyDao {
}