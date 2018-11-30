package com.atypon.web.controller;

import com.atypon.domain.ContentLicence;
import com.atypon.domain.UserContentLicence;
import com.atypon.service.ContentLicenceService;
import com.atypon.service.LicenceService;
import com.atypon.service.UserContentLicenceService;
import com.atypon.web.form.ContentLicenceForm;
import com.atypon.web.form.UserContentLicenceForm;
import com.atypon.web.validator.ContentLicenceValidator;
import com.atypon.web.validator.UserContentLicenceValidator;
import com.atypon.web.validator.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LicenceController implements Controller {

    private static final String CONTENT_LICENCE_PAGE = "contentLicence.jsp";
    private static final String USER_LICENCE_PAGE = "userLicence.jsp";

    private final LicenceService licenceService;
    private final ContentLicenceService contentLicenceService;
    private final UserContentLicenceService userContentLicenceService;

    private final Validator<ContentLicenceForm> contentLicenceValidator =
            new ContentLicenceValidator();
    private final Validator<UserContentLicenceForm> userContentLicenceValidator =
            new UserContentLicenceValidator();

    public LicenceController(ServletContext context) {
        licenceService = (LicenceService) context.getAttribute("licenceService");
        contentLicenceService = (ContentLicenceService) context.getAttribute("contentLicenceService");
        userContentLicenceService = (UserContentLicenceService) context.getAttribute("userContentLicenceService");
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
            case "deleteContentLicence":
                return handleDeleteContentLicence(request, response);
            case "userLicence":
                return handleUserLicence(request, response);
            case "createUserLicence":
                return handleCreateUserLicence(request, response);
            case "deleteUserLicence":
                return handleDeleteUserLicence(request, response);
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
        ContentLicenceForm form = extractContentLicenceForm(request);
        request.setAttribute("form", form);
        List<String> errors = contentLicenceValidator.validate(form);
        if (setAsBadRequestIfBadMethod(request, response, "POST")) {
            return null;
        }
        if (errors.isEmpty()) {
            ContentLicence contentLicence = extractContentLicence(form);
            request.setAttribute("result",
                    contentLicenceService.create(contentLicence) ?
                            "Licence Created" : "Licence Cannot be Created");

        } else {
            request.setAttribute("errors", errors);
        }
        request.setAttribute("licences", licenceService.getAll());
        request.setAttribute("contentsLicences", contentLicenceService.getAll());
        return CONTENT_LICENCE_PAGE;
    }


    private String handleDeleteContentLicence(HttpServletRequest request, HttpServletResponse response) {
        ContentLicenceForm form = extractContentLicenceForm(request);
        request.setAttribute("form", form);
        if (setAsBadRequestIfBadMethod(request, response, "GET")) {
            return null;
        }
        String contentLicenceId = getString("id", request);
        if (contentLicenceValidator.isInteger(contentLicenceId)) {
            request.setAttribute("result",
                    contentLicenceService.delete(Integer.parseInt(contentLicenceId)) ?
                            "Licence Deleted" : "Licence Cannot be Deleted");

        } else {
            List<String> errors = new ArrayList<>();
            errors.add("ID is empty or not number");
            request.setAttribute("errors", errors);
        }
        request.setAttribute("licences", licenceService.getAll());
        request.setAttribute("contentsLicences", contentLicenceService.getAll());
        return CONTENT_LICENCE_PAGE;
    }

    private String handleUserLicence(HttpServletRequest request, HttpServletResponse response) {
        if (setAsBadRequestIfBadMethod(request, response, "GET")) {
            return null;
        }
        request.setAttribute("contentsLicences", contentLicenceService.getAll());
        request.setAttribute("usersLicences", userContentLicenceService.getAll());
        return USER_LICENCE_PAGE;
    }


    private String handleCreateUserLicence(HttpServletRequest request, HttpServletResponse response) {
        if (setAsBadRequestIfBadMethod(request, response, "POST")) {
            return null;
        }
        UserContentLicenceForm form = extractUserLicenceForm(request);
        request.setAttribute("form", form);
        List<String> errors = userContentLicenceValidator.validate(form);
        if (errors.isEmpty()) {
            UserContentLicence userLicence = extractUserLicence(form);
            request.setAttribute("result",
                    userContentLicenceService.create(userLicence) ?
                            "Licence Created" : "Licence Cannot be Created");

        } else {
            request.setAttribute("errors", errors);
        }
        request.setAttribute("contentsLicences", contentLicenceService.getAll());
        request.setAttribute("usersLicences", userContentLicenceService.getAll());
        return USER_LICENCE_PAGE;
    }

    private String handleDeleteUserLicence(HttpServletRequest request, HttpServletResponse response) {
        if (setAsBadRequestIfBadMethod(request, response, "GET")) {
            return null;
        }
        UserContentLicenceForm form = extractUserLicenceForm(request);
        request.setAttribute("form", form);
        String userContentLicenceId = getString("id", request);
        if (userContentLicenceValidator.isEmpty(userContentLicenceId) ||
                userContentLicenceValidator.isInteger(userContentLicenceId)) {
            request.setAttribute("result",
                    userContentLicenceService.delete(Integer.parseInt(userContentLicenceId)) ?
                            "Licence Deleted" : "Licence Cannot be Deleted");

        } else {
            List<String> errors = new ArrayList<>();
            errors.add("ID is empty or not number");
            request.setAttribute("errors", errors);
        }
        request.setAttribute("licences", licenceService.getAll());
        request.setAttribute("contentsLicences", contentLicenceService.getAll());
        return USER_LICENCE_PAGE;
    }

    private UserContentLicence extractUserLicence(UserContentLicenceForm form) {
        UserContentLicence userLicence = new UserContentLicence();
        userLicence.setUsername(form.getUsername().trim());
        userLicence.setBody(form.getBody() == null ? "" : form.getBody().trim());
        userLicence.setContentLicenceId(Integer.parseInt(form.getContentLicenceId()));
        return userLicence;
    }

    private UserContentLicenceForm extractUserLicenceForm(HttpServletRequest request) {
        UserContentLicenceForm form = new UserContentLicenceForm();
        form.setUsername(getString("username", request));
        form.setContentLicenceId(getString("contentLicenceId", request));
        form.setBody(getString("licenceBody", request));
        return form;
    }


    private ContentLicenceForm extractContentLicenceForm(HttpServletRequest request) {
        ContentLicenceForm form = new ContentLicenceForm();
        form.setLicenceName(getString("licenceName", request));
        form.setContentId(getString("contentId", request));
        form.setBody(getString("licenceBody", request));
        return form;
    }

    private ContentLicence extractContentLicence(ContentLicenceForm form) {
        ContentLicence contentLicence = new ContentLicence();
        contentLicence.setLicenceName(form.getLicenceName().trim());
        contentLicence.setContentId(form.getContentId().trim());
        contentLicence.setBody(form.getBody() != null ? form.getBody().trim() : "");
        return contentLicence;
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
