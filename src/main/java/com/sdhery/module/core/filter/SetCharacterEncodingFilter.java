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
        //非加载URL
        if (!FilterUtil.doInclude(request, response, getFilterConfig())) {
            filterChain.doFilter(request, response);
            return;
        }

        //过滤的URL
        if (FilterUtil.doExclude(request, response, getFilterConfig())) {
            filterChain.doFilter(request, response);
            return;
        }

        super.doFilterInternal(request, response, filterChain);
    }
}
