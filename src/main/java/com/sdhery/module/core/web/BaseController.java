package com.sdhery.module.core.web;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.ui.ModelMap;

import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-6-7
 * Time: 上午10:12
 * To change this template use File | Settings | File Templates.
 */
public class BaseController {
    public void copyProperties(Object target, Object source) {
        try {
            BeanUtils.copyProperties(target, source);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void setSuccess(ModelMap model) {
        model.put("success", true);
    }

    public void setFailure(ModelMap model) {
        model.put("success", false);
    }
}
