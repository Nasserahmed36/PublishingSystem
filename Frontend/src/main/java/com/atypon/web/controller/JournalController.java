package com.atypon.web.controller;


import com.atypon.service.JournalService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JournalController implements Controller {

    private final JournalService journalService;

    public JournalController(ServletContext context) {
        journalService = (JournalService) context.getAttribute("journalService");
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
        return null;
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


}
