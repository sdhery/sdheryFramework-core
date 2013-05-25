package com.sdhery.core.service.impl;

import com.sdhery.core.dao.EntityDao;
import com.sdhery.core.dao.ISysObjectKeyDao;
import com.sdhery.core.domain.SysObjectKey;
import com.sdhery.core.service.ISysObjectKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-25
 * Time: 下午5:02
 * To change this template use File | Settings | File Templates.
 */
@Service("sysObjectKeyService")
public class SysObjectKeyService extends BaseService<SysObjectKey,String> implements ISysObjectKeyService {
    @Autowired
    @Qualifier("sysObjectKeyDao")
    private ISysObjectKeyDao sysObjectKeyDao;

    protected EntityDao<SysObjectKey, String> getEntityDao() {
        return sysObjectKeyDao;
    }
}
