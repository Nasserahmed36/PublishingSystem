package com.atypon.web.controller;

import com.atypon.domain.Identity;
import com.atypon.service.IdentityService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController implements Controller {
    private static final String ADMIN_TYPE = "admin";
    private static final String LOGIN_PAGE = "login.jsp";
    private final IdentityService identityService;

    public LoginController(ServletContext context) {
        identityService = (IdentityService) context.getAttribute("identityService");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operation = extractOperation(request);
        switch (operation) {
            case "loginPage":
                return handleLoginPage(request, response);
            case "login":
                return handleLogin(request, response);

        }
        return null;
    }

    private String handleLoginPage(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return LOGIN_PAGE;
    }

    private String handleLogin(HttpServletRequest request, HttpServletResponse response) {
        String username = getString("username", request);
        String password = getString("password", request);
        Identity identity = identityService.get(username, password);
        if (identity != null && identity.getType().equals(ADMIN_TYPE)) {
            request.getSession().setAttribute("user", identity);
            return "redirect:/identities/all?result=Welcome " + identity.getUsername();
        } else {
            request.getSession().invalidate();
            request.setAttribute("result", "Incorrect Admin Username or password");
            return LOGIN_PAGE;
        }
    }

    private String getString(String key, HttpServletRequest request) {
        String value = request.getParameter(key);
        if (value != null) {
            return value.trim();
        }
        return null;
    }

    private String extractOperation(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String res[] = uri.split("/");
        if (res.length >= 4)
            return res[3];
        else
            return "login";
    }

}
