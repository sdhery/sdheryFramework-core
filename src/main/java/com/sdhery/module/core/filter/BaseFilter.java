package com.sdhery.module.core.filter;

import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-7-13
 * Time: 下午4:46
 * To change this template use File | Settings | File Templates.
 */
public class BaseFilter {

    /**
     * 排除的url
     *
     * @param request
     * @param response
     * @param filterConfig
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public boolean doExclude(ServletRequest request, ServletResponse response, FilterConfig filterConfig) throws ServletException, IOException {
        String excludeUrls = filterConfig.getInitParameter("excludeUrls");
        if (excludeUrls == null) {
            return false;
        }
        String[] excludes = excludeUrls.split(";");
        HttpServletRequest httprequest = (HttpServletRequest) request;
        String path = httprequest.getServletPath();
        //需要忽略的URL
        for (int i = 0; i < excludes.length; i++) {
            String regx = excludes[i].replaceAll("\\.", "\\\\.");
            regx = regx.replaceAll("\\*", "\\.*");
            if (excludes[i].endsWith("/")) {
                regx += ".*";
            }

            if (path.matches(regx)) {
                return true;
            }
        }
        return false;

    }

    /**
     * 包含的url
     *
     * @param request
     * @param response
     * @param filterConfig
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public boolean doInclude(ServletRequest request, ServletResponse response, FilterConfig filterConfig) throws ServletException, IOException {
        String includeUrls = filterConfig.getInitParameter("includeUrls");
        if (StringUtils.isBlank(includeUrls)) {
            return true;
        }
        String[] includes = includeUrls.split(";");
        HttpServletRequest httprequest = (HttpServletRequest) request;
        String path = httprequest.getServletPath();
        //需要加载的URL
        for (int i = 0; i < includes.length; i++) {
            String regx = includes[i].replaceAll("\\.", "\\\\.");
            regx = regx.replaceAll("\\*", "\\.*");
            if (includes[i].endsWith("/")) {
                regx += ".*";
            }

            if (path.matches(regx)) {
                return true;
            }
        }
        return false;
    }
}
