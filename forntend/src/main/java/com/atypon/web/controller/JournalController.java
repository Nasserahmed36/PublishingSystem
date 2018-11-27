package com.atypon.web.controller;


import com.atypon.service.IssueService;
import com.atypon.service.JournalService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JournalController implements Controller {

    private final String JOURNALS_VIEW = "journalList.jsp";
    private final String ISSUES_VIEW = "issueList.jsp";

    private final IssueService issueService;
    private final JournalService journalService;


    public JournalController(ServletContext context) {
        journalService = (JournalService) context.getAttribute("journalService");
        issueService = (IssueService) context.getAttribute("issueService");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response) {
        String printIssn = extractPrintIssn(request);
        if (printIssn == null) {
            return getAllJournals(request, response);
        } else {
            return getAllIssuesFor(request,response,printIssn);
        }
    }


    private String getAllJournals(HttpServletRequest request, HttpServletResponse response) {
        String discipline = request.getParameter("discipline");
        if (isEmpty(discipline)) {
            return handleAllWithoutDiscipline(request, response);
        } else {
            return handleAllWithDiscipline(request, response, discipline);
        }

    }

    private String handleAllWithoutDiscipline(HttpServletRequest request,
                                              HttpServletResponse response) {
        request.setAttribute("journals", journalService.getAll());
        return JOURNALS_VIEW;
    }

    private String handleAllWithDiscipline(HttpServletRequest request,
                                           HttpServletResponse response,
                                           String discipline) {
        request.setAttribute("journals", journalService.getByDiscipline(discipline));
        return JOURNALS_VIEW;
    }


    private String getAllIssuesFor(HttpServletRequest request, HttpServletResponse response, String printIssn) {
        String volume = request.getParameter("volume");
        request.setAttribute("lastVolume", issueService.getLastVolume(printIssn));
        if (isNumber(volume)) {
            return handleAllWithVolume(request, response,printIssn,
                    Integer.parseInt(volume));
        } else {
            return handleAllWithoutVolume(request, response,printIssn);
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
