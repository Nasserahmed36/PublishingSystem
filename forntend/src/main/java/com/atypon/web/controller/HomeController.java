package com.atypon.web.controller;

import com.atypon.service.ArticleService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {

    private final String HOME_VIEW = "homePage.jsp";

    private final ArticleService articleService;


    public HomeController(ServletContext context) {
        articleService = (ArticleService) context.getAttribute("articleService");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response) {
        String operation = extractOperation(request);
        switch (operation) {
            case "main":
                return handleMain(request, response);
        }
        return null;
    }

    private String handleMain(HttpServletRequest request, HttpServletResponse response) {
        return HOME_VIEW;
    }


    private String extractOperation(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String res[] = uri.split("/");
        if (res.length >= 4)
            return res[3];
        else
            return "";
    }
}
