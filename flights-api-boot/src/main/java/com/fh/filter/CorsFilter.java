package com.fh.filter;

import org.springframework.http.HttpHeaders;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //设置允许跨域的响应头
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,"http://localhost:8080");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,"content-type,abc,token");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,"GET, POST, PUT, HEAD, PATCH, DELETE, OPTIONS, TRACE");


        //判断本次请求是否为预检请求
        String method = request.getMethod();
        if(!method.equalsIgnoreCase("options")){
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

}
