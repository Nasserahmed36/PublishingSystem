package com.atypon.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServiceFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletRequest;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (isStaticResource(servletRequest)) {
            filterChain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/Dispatcher").forward(request, response);
        }

    }

    private boolean isStaticResource(ServletRequest servletRequest) {
        return true;
    }
}
