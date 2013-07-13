package com.sdhery.module.core.web.impl;

import com.sdhery.module.core.web.IWebContext;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-7-13
 * Time: 下午5:26
 * To change this template use File | Settings | File Templates.
 */
public class WebContextFactory {
    private static String localUploadPath;
    private static String webRealPath;
     private static ThreadLocal<IWebContext> ctxStore = new ThreadLocal();
}
