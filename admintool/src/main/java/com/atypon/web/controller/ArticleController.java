package com.atypon.web.controller;

import com.atypon.service.ArticleService;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ArticleController implements Controller {

    private static final String ARTICLES_PAGE = "articles.jsp";
    private final ArticleService articleService;

    public ArticleController(ServletContext context) {
        articleService = (ArticleService) context.getAttribute("articleService");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String operation = extractOperation(request);
        switch (operation) {
            case "all":
                return handleGetAll(request, response);
        }
        return null;
    }


    private String extractOperation(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String res[] = uri.split("/");
        if (res.length >= 4)
            return res[3];
        else
            return "all";
    }


    private String handleGetAll(HttpServletRequest request, HttpServletResponse response) {
        if (setAsBadRequestIFBadMethod(request, response, "GET")) {
            return null;
        }
        request.setAttribute("articles", articleService.getAll());
        return ARTICLES_PAGE;
    }


    private boolean setAsBadRequestIFBadMethod(HttpServletRequest request,
                                               HttpServletResponse response,
                                               String expectedMethod) {
        if (!request.getMethod().equals(expectedMethod)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return true;
        }
        return false;
    }


}
