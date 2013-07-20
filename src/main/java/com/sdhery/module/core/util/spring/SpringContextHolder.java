package com.sdhery.module.core.util.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.Class;import java.lang.IllegalStateException;import java.lang.Object;import java.lang.String;import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-6-9
 * Time: 下午2:49
 * To change this template use File | Settings | File Templates.
 */
public class SpringContextHolder implements ApplicationContextAware {
    private static Log log = LogFactory.getLog(SpringContextHolder.class);
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext context) {
        if (applicationContext != null) {
            log.error("ApplicationContextHolder already holded 'applicationContext'.");
        }
        applicationContext = context;
        log.debug("holded applicationContext,displayName:" + applicationContext.getDisplayName());
    }

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("'applicationContext' property is null,ApplicationContextHolder not yet init.");
        }
        return applicationContext;
    }

    public static <T> T getBean(Class<T> requiredType) {
        return getApplicationContext().getBean(requiredType);
    }

    public static Object getBean(String beanName) {
        return getApplicationContext().getBean(beanName);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return getApplicationContext().getBean(name,requiredType);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> requiredType) {
        return getApplicationContext().getBeansOfType(requiredType);
    }

    public static void cleanHolder() {
        applicationContext = null;
    }
}
