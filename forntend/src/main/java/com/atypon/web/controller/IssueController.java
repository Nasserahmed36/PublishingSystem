package com.atypon.web.controller;


import com.atypon.domain.Article;
import com.atypon.domain.Issue;
import com.atypon.managing.ContentManager;
import com.atypon.service.ArticleService;
import com.atypon.service.IssueService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IssueController implements Controller {

    private final String ARTICLES_VIEW = "articleList.jsp";

    private final ArticleService articleService;
    private final IssueService issueService;
    private final ContentManager contentManager;


    public IssueController(ServletContext context) {
        String contentPath = context.getRealPath("publish/content");
        articleService = (ArticleService) context.getAttribute("articleService");
        issueService = (IssueService) context.getAttribute("issueService");
        contentManager = new ContentManager(contentPath);
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response) {
        String jouranlPrintIssn = extractPrintIssn(request);
        String issueDoi = extractIssueDoi(request);
        if (issueDoi == null) {
            return null;
        } else {
            return getAllArticlesFor(request, response, jouranlPrintIssn, issueDoi);
        }
    }


    private String getAllArticlesFor(HttpServletRequest request, HttpServletResponse response,
                                     String jouranlPrintIssn, String issueDoi) {
        request.setAttribute("subjectsMap", articleService.getSubjectToArticlesMap(jouranlPrintIssn, issueDoi));
        Issue issue = issueService.get(jouranlPrintIssn, issueDoi);
        Article article = articleService.getFromIssue(issue);
        String issueCoverAbsolutePath = contentManager.getIssueCoverPath(article, issue);
        String issueCoverRelativePath = issueCoverAbsolutePath.split(request.getRealPath("publish") + "/")[1];
        request.setAttribute("issueCover", issueCoverRelativePath);
        request.setAttribute("issue", issue);
        return ARTICLES_VIEW;
    }


    private String extractIssueDoi(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String res[] = uri.split("/");
        if (res.length >= 5)
            return res[4];
        else
            return null;
    }


    private String extractPrintIssn(HttpServletRequest request) {
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
