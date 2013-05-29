package com.sdhery.core.helper;

import com.sdhery.core.service.ISysObjectKeyService;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-29
 * Time: 上午10:41
 * To change this template use File | Settings | File Templates.
 */
public class CoreServiceManager {
    public static ISysObjectKeyService sysObjectKeyService;

    public void setSysObjectKeyService(ISysObjectKeyService sysObjectKeyService) {
        CoreServiceManager.sysObjectKeyService = sysObjectKeyService;
    }
}
