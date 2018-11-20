package com.atypon.web.controller;


import com.atypon.service.IssueService;
import com.atypon.service.JournalService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IssueController implements Controller {

    private final String JOURNALS_VIEW = "journalList.jsp";
    private final String ISSUES_VIEW = "issueList.jsp";

    private final IssueService issueService;
    private final JournalService journalService;


    public IssueController(ServletContext context) {
        journalService = (JournalService) context.getAttribute("journalService");
        issueService = (IssueService) context.getAttribute("issueService");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response) {
        String issueDoi = extractIssueDoi(request);
        if (issueDoi == null) {
            return null;
        } else {
            return getAllArticlesFor(request,response,issueDoi);
        }
    }



    private String getAllArticlesFor(HttpServletRequest request, HttpServletResponse response, String issueDoi) {
        String volume = request.getParameter("volume");
        request.setAttribute("lastVolume", issueService.getLastVolume(issueDoi));
        if (isNumber(volume)) {
            return handleAllWithVolume(request, response,issueDoi,
                    Integer.parseInt(volume));
        } else {
            return handleAllWithoutVolume(request, response,issueDoi);
        }
    }


    private String handleAllWithoutVolume(HttpServletRequest request,
                                          HttpServletResponse response,
                                          String journalPrintIssn) {
        request.setAttribute("issues", issueService.getBy(journalPrintIssn));
        return ISSUES_VIEW;
    }

    private String handleAllWithVolume(HttpServletRequest request,
                                       HttpServletResponse response,
                                       String journalPrintIssn,
                                       int volume) {
        request.setAttribute("issues", issueService.getBy(journalPrintIssn, volume));
        return ISSUES_VIEW;
    }

    private boolean isNumber(String string) {
        return StringUtils.isNumeric(string);
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
