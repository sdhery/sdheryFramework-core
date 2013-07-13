package com.sdhery.module.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-7-13
 * Time: 下午5:14
 * To change this template use File | Settings | File Templates.
 */
public interface IWebContext {
    HttpServletRequest getRequest();

    HttpServletResponse getResponse();
}
