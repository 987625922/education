package com.project.gelingeducation.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@Slf4j
//@Component
//@WebFilter(urlPatterns = {"/*"}, filterName = "shiroFilter",initParams = {
//        @WebInitParam(name = "targetFilterLifecycle", value = "true")
//})
public class ShiroFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      log.debug("==> ShiroFilter 初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("==> ShiroFilter doFilter");

    }

    @Override
    public void destroy() {

    }
}
