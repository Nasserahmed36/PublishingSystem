package com.atypon.web.controller;


import com.atypon.managing.ContentManager;
import com.atypon.service.IssueService;
import com.atypon.service.JournalService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArticleController implements Controller {

    private static final String JOURNALS_VIEW = "journalList.jsp";
    private static final String ISSUES_VIEW = "issueList.jsp";
    private final String contentPath;

    private final IssueService issueService;
    private final JournalService journalService;
    private final ContentManager contentManager;


    public ArticleController(ServletContext context) {
        contentPath = context.getRealPath("publish/content");
        journalService = (JournalService) context.getAttribute("journalService");
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

        return null;
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
        if (res.length >= 5)
            return res[4];
        else
            return "";
    }


    private boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }


}
