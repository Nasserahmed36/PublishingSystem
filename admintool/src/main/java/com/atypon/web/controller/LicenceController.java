package com.atypon.web.controller;

import com.atypon.service.ContentLicenceService;
import com.atypon.service.LicenceService;
import com.atypon.web.form.ContentLicenceForm;
import com.atypon.web.validator.ContentLicenceValidator;
import com.atypon.web.validator.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LicenceController implements Controller {

    private static final String CONTENT_LICENCE_PAGE = "contentLicence.jsp";

    private final LicenceService licenceService;
    private final ContentLicenceService contentLicenceService;

    private final Validator<ContentLicenceForm> contentLicenceFormValidator =
            new ContentLicenceValidator();

    public LicenceController(ServletContext context) {
        licenceService = (LicenceService) context.getAttribute("licenceService");
        contentLicenceService = (ContentLicenceService) context.getAttribute("contentLicenceService");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String operation = extractOperation(request);
        switch (operation) {
            case "contentLicence":
                return handleContentLicence(request, response);
            case "createContentLicence":
                return handleCreateContentLicence(request, response);
        }
        return null;
    }


    private String handleContentLicence(HttpServletRequest request, HttpServletResponse response) {
        if (setAsBadRequestIfBadMethod(request, response, "GET")) {
            return null;
        }
        request.setAttribute("licences", licenceService.getAll());
        request.setAttribute("contentsLicences", contentLicenceService.getAll());
        return CONTENT_LICENCE_PAGE;
    }

    private String handleCreateContentLicence(HttpServletRequest request, HttpServletResponse response) {
        JournalForm form = extractJournalForm(request);
        request.setAttribute("form", form);
        List<String> errors = validator.validate(form);
        if (setAsBadRequestIFBadMethod(request, response, "POST")) {
            return null;
        }
        if (errors.isEmpty()) {
            Journal journal = extractJournal(form);
            request.setAttribute("result",
                    journalService.create(journal) ?
                            "Journal Created" : "Journal Cannot be Created");

        } else {
            request.setAttribute("errors", errors);
        }
        return "journal.jsp";
    }


    private String extractOperation(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String res[] = uri.split("/");
        if (res.length >= 4)
            return res[3];
        else
            return "all";
    }


    private boolean setAsBadRequestIfBadMethod(HttpServletRequest request,
                                               HttpServletResponse response,
                                               String expectedMethod) {
        if (!request.getMethod().equals(expectedMethod)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return true;
        }
        return false;
    }


    private String getString(String key, HttpServletRequest request) {
        String value = request.getParameter(key);
        if (value != null) {
            return value.trim();
        }
        return null;
    }
}
