package com.atypon.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/login";
        String loginPage = loginURI + "/" + "loginPage";
        String requestUri = request.getRequestURI();

        boolean isUserLoggedIn = session != null && session.getAttribute("user") != null;
        boolean isLoginRequest = requestUri.startsWith(loginURI);
        boolean isStaticResource = isStaticResource(request);

        if (isUserLoggedIn || isLoginRequest || isStaticResource) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginPage);
        }
    }

    private boolean isStaticResource(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String staticResourcesPath = request.getContextPath() + "/admin/assets";
        return requestUri.startsWith(staticResourcesPath);
    }

}