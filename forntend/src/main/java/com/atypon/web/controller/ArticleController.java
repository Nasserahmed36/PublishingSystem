package com.atypon.web.controller;


import com.atypon.acs.AuthenticationService;
import com.atypon.domain.Article;
import com.atypon.domain.Identity;
import com.atypon.domain.Issue;
import com.atypon.domain.UserRequest;
import com.atypon.managing.ContentManager;
import com.atypon.service.ArticleService;
import com.atypon.service.IssueService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArticleController implements Controller {

    private static final String ARTICLE_VIEW = "article.jsp";
    private static final String NO_ACCESS_VIEW = "noAccess.jsp";

    private final IssueService issueService;
    private final ArticleService articleService;
    private final ContentManager contentManager;
    private final AuthenticationService authenticationService;


    public ArticleController(ServletContext context) {
        String contentPath = context.getRealPath("publish/content");
        articleService = (ArticleService) context.getAttribute("articleService");
        issueService = (IssueService) context.getAttribute("issueService");
        contentManager = new ContentManager(contentPath);
        authenticationService = (AuthenticationService) context.getAttribute("authenticationService");
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
        if (!hasAccess(request, response, doi)) {
            return NO_ACCESS_VIEW;
        }
        Article article = articleService.get(doi);
        Issue issue = issueService.get(article.getIssueDoi());
        String viewAbsolutePath = contentManager.getArticlePath(article, issue);
        String viewRelativePath = viewAbsolutePath.split(request.getRealPath("publish") + "/")[1];
        String issueCoverAbsolutePath = contentManager.getIssueCoverPath(article, issue);
        String issueCoverRelativePath = issueCoverAbsolutePath.split(request.getRealPath("publish") + "/")[1];
        request.setAttribute("article", article);
        request.setAttribute("articleView", viewRelativePath);
        request.setAttribute("issueCover", issueCoverRelativePath);
        request.setAttribute("issue", issue);
        return ARTICLE_VIEW;
    }

    private boolean hasAccess(HttpServletRequest request, HttpServletResponse response, String doi) {
        Identity identity = (Identity) request.getSession().getAttribute("user");
        UserRequest userRequest = new UserRequest();
        userRequest.setIp(request.getRemoteAddr());
        userRequest.setUrl(request.getRequestURL().toString());
        userRequest.setMethod(request.getMethod());
        return authenticationService.hasAccess(userRequest, identity == null ? null : identity.getUsername(), doi);
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
