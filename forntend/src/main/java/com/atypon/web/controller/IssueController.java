package com.atypon.web.controller;


import com.atypon.service.ArticleService;
import com.atypon.service.IssueService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IssueController implements Controller {

    private final String ARTICLES_VIEW = "articleList.jsp";

    private final ArticleService articleService;


    public IssueController(ServletContext context) {
        articleService = (ArticleService) context.getAttribute("articleService");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response) {
        String issueDoi = extractIssueDoi(request);
        if (issueDoi == null) {
            return null;
        } else {
            return getAllArticlesFor(request, response, issueDoi);
        }
    }


    private String getAllArticlesFor(HttpServletRequest request, HttpServletResponse response, String issueDoi) {
        request.setAttribute("subjectsMap", articleService.getSubjectToArticlesMap(issueDoi));
        return ARTICLES_VIEW;
    }


    private String extractIssueDoi(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String res[] = uri.split("/");
        if (res.length >= 4)
            return res[3];
        else
            return null;
    }


    private boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }


}
