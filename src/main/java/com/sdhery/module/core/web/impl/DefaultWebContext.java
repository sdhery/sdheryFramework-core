package com.sdhery.module.core.web.impl;

import com.sdhery.module.core.web.IWebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-7-13
 * Time: 下午5:17
 * To change this template use File | Settings | File Templates.
 */
public class DefaultWebContext implements IWebContext {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;


    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
