package com.atypon.web.controller;


import com.atypon.service.IssueService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IssueController implements Controller {

    private final String ISSUES_VIEW = "issueList.jsp";

    private final IssueService issueService;


    public IssueController(ServletContext context) {
        issueService = (IssueService) context.getAttribute("issueService");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response) {
        String operation = extractOperation(request);
        switch (operation) {
            case "all":
                return handleAll(request, response);
        }
        return null;
    }


    private String handleAll(HttpServletRequest request, HttpServletResponse response) {
        String journalPrintIssn = request.getParameter("journalPrintIssn");
        String volume = request.getParameter("volume");
        request.setAttribute("lastVolume", issueService.getLastVolume(journalPrintIssn));
        if (!isNumber(volume)) {
            return handleAllWithoutVolume(request, response);
        } else {
            return handleAllWithVolume(request, response, Integer.parseInt(volume));
        }


    }

    private String handleAllWithoutVolume(HttpServletRequest request,
                                          HttpServletResponse response) {
        request.setAttribute("issues", issueService.getAll());
        return ISSUES_VIEW;
    }

    private String handleAllWithVolume(HttpServletRequest request,
                                       HttpServletResponse response,
                                       int volume) {
        request.setAttribute("issues", issueService.getByVolume(volume));
        return ISSUES_VIEW;
    }


    private String extractOperation(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String res[] = uri.split("/");
        if (res.length >= 4)
            return res[3];
        else
            return "all";
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


    private boolean isNumber(String string) {
        return StringUtils.isNumeric(string);
    }


}
