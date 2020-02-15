package com.project.gelingeducation.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//设置处理中文乱码的问题还是出现，等待解决 http://localhost:8081/get5/baiduyixia/info
//@Component
//@WebFilter(urlPatterns = "/*", filterName = "characterEncodingFilter")
public class CharacterEncodingFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        //POST的乱码解决方案
        request.setCharacterEncoding("utf-8");

        //返回数据的乱码解决方案
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //用动态代理拦截,增强getParameter()后,放行
        chain.doFilter((ServletRequest) Proxy.newProxyInstance(CharacterEncodingFilter.class.getClassLoader(), request.getClass().getInterfaces(),
                new InvocationHandler() { //直接实现接口
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        //如果请求方式是POST,则不用增强,直接调用目标对象的方法
                        if (request.getMethod().equalsIgnoreCase("POST")) {
                            return method.invoke(request, args);
                        }
                        String methodName = method.getName();
                        //如果传递进来的方法不是getParameter(),则不用增强
                        if (!methodName.equals("getParameter")) {
                            return method.invoke(request, args);
                        }
                        //为GET,并且是getParameter(),...
                        String value = (String) method.invoke(request, args);
                        if (value != null) {
                            value = new String(value.getBytes("iso8859-1"), "utf-8");
                        }
                        return value;
                    }
                }), response);
    }

    public void init(FilterConfig filterConfig) {

    }

    public void destroy() {

    }

}
