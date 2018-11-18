package com.atypon.web.controller;


import com.atypon.service.JournalService;
import com.atypon.web.form.JournalForm;
import com.atypon.web.validator.JournalValidator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JournalController implements Controller {

    private final String JOURNALS_VIEW = "journalList.jsp";

    private final JournalService journalService;
    private final JournalValidator validator;


    public JournalController(ServletContext context) {
        journalService = (JournalService) context.getAttribute("journalService");
        validator = new JournalValidator();
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String operation = extractOperation(request);
        switch (operation) {
            case "all":
                return handleAll(request, response);
        }
        return null;
    }


    private String handleAll(HttpServletRequest request, HttpServletResponse response) {
        String discipline = request.getParameter("discipline");
        if (validator.isEmpty(discipline)) {
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


    private boolean setAsBadRequestIFBadMethod(HttpServletRequest request,
                                               HttpServletResponse response,
                                               String expectedMethod) {
        if (!request.getMethod().equals(expectedMethod)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return true;
        }
        return false;
    }

    private JournalForm extractJournalForm(HttpServletRequest request) {
        JournalForm form = new JournalForm();
        form.setId(request.getParameter("id").trim());
        form.setElectronicIssn(request.getParameter("eIssn").trim());
        form.setPrintIssn(request.getParameter("pIssn").trim());
        form.setElectronicIssn(request.getParameter("eIssn").trim());
        form.setTitle(request.getParameter("title").trim());
        form.setPublisherName(request.getParameter("publisherName").trim());
        form.setPublisherLocation(request.getParameter("publisherLocation").trim());
        form.setDiscipline(request.getParameter("discipline").trim());
        return form;
    }




}
