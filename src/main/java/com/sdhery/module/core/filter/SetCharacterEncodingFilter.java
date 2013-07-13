package com.sdhery.module.core.filter;

import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-7-13
 * Time: 下午4:56
 * 继承了springMVC CharacterEncodingFilter,用于解决乱码问题
 */
public class SetCharacterEncodingFilter extends CharacterEncodingFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!this.doInclude(request, response, filterChain)) {
            filterChain.doFilter(request, response);
            return;
        }

        //对配置的excludeUrls参数包含的URL进行过滤,例如：/OurHome/;/OurHome/*;/OurHome/*.jpg
        if (this.doExclude(request, response, filterChain)) {
            filterChain.doFilter(request, response);
            return;
        }

        WebContextService ctx = (WebContextService) WebContextFactory.getWebContext();
        if (((request instanceof HttpServletRequest)) && ((response instanceof HttpServletResponse))) {
            ctx.setRequest((HttpServletRequest) request);
            ctx.setResponse((HttpServletResponse) response);
        }
        super.doFilterInternal(request, response, filterChain);
    }
}
