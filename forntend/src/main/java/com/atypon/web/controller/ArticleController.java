package com.atypon.web.controller;


import com.atypon.domain.Article;
import com.atypon.managing.ContentManager;
import com.atypon.service.ArticleService;
import com.atypon.service.IssueService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArticleController implements Controller {

    private static final String ARTICLE_VIEW = "article.jsp";

    private final IssueService issueService;
    private final ArticleService articleService;
    private final ContentManager contentManager;


    public ArticleController(ServletContext context) {
        String contentPath = context.getRealPath("publish/content");
        articleService = (ArticleService) context.getAttribute("articleService");
        issueService = (IssueService) context.getAttribute("issueService");
        contentManager = new ContentManager(contentPath);
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response) {
        String operation = extractOperation(request);
        switch (operation) {
            case "view":
                return handleView(request, response);
            case "pdf":
                return null;

        }
        return null;
    }

    private String handleView(HttpServletRequest request, HttpServletResponse response) {
        String doi = extractDoi(request);
        Article article = articleService.get(doi);
        String viewAbsolutePath = contentManager.getArticlePath(doi);
        String viewRelativePath = viewAbsolutePath.split(request.getRealPath("publish") + "/")[1];
        request.setAttribute("article", article);
        request.setAttribute("articleView", viewRelativePath);
        return ARTICLE_VIEW;
    }


    private boolean isNumber(String string) {
        return StringUtils.isNumeric(string);
    }


    private String extractOperation(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String res[] = uri.split("/");
        if (res.length >= 4)
            return res[3];
        else
            return "";
    }

    private String extractDoi(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String res[] = uri.split("/");
        if (res.length >= 6)
            return res[4] + "/" + res[5];
        else
            return "";
    }


    private boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }


}
