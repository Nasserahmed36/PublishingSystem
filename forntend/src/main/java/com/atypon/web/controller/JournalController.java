package com.atypon.web.controller;


import com.atypon.service.JournalService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JournalController implements Controller {

    private final String JOURNALS_VIEW = "journalList.jsp";

    private final JournalService journalService;


    public JournalController(ServletContext context) {
        journalService = (JournalService) context.getAttribute("journalService");
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


    private String extractOperation(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String res[] = uri.split("/");
        if (res.length >= 4)
            return res[3];
        else
            return "all";
    }



    public boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }





}
