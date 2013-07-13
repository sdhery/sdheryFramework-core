package com.sdhery.module.core.web.impl;

import com.sdhery.module.core.web.IWebContext;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-7-13
 * Time: 下午5:26
 * To change this template use File | Settings | File Templates.
 */
public class WebContextFactory implements ServletContextAware {
    private static String localUploadPath;
    private static String webRealPath;
    private static ThreadLocal<IWebContext> ctxStore = new ThreadLocal();
    private static ServletContext servletContext;

    public static void setWebContext(IWebContext ctx) {
        ctxStore.set(ctx);
    }

    public static IWebContext getWebContext() {
        IWebContext ctx = (IWebContext) ctxStore.get();
        if (ctx == null) {
            ctx = new DefaultWebContext();
            setWebContext(ctx);
        }
        return (IWebContext) ctxStore.get();
    }

    public static String getLocalUploadPath() {
        if (localUploadPath != null) {
            return localUploadPath;
        }
        localUploadPath = servletContext.getRealPath("/") + File.separatorChar + "upload" + File.separatorChar;
        return localUploadPath;
    }

    public static synchronized void setContext(ServletContext servletContext) {
        WebContextFactory.servletContext = servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        setContext(servletContext);
    }

    public static String getWebRealPath() {
        if (webRealPath != null) {
            return webRealPath;
        }
        webRealPath = servletContext == null ? null : servletContext.getRealPath("/");
        return webRealPath;
    }

    public static String getContentPath() {
        return servletContext.getContextPath();
    }

    public static synchronized void setWebRootPath(String webRootPath) {
        webRealPath = webRootPath;
    }
}
