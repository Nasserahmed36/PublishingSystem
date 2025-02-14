package com.atypon.web.servlet;

import com.atypon.web.controller.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DispatcherServlet", urlPatterns = {"/identities/*", "/journal/*", "/backstage/*",
        "/article/*", "/licence/*", "/login/*"})
@MultipartConfig
public class DispatcherServlet extends HttpServlet {

    private static final String JSPS_FILE = "/admin";

    private Controller identityController;
    private Controller journalController;
    private Controller backstageController;
    private Controller articleController;
    private Controller licenceController;
    private Controller loginController;

    @Override
    public void init() throws ServletException {
        super.init();
        identityController = new IdentityController(getServletContext());
        journalController = new JournalController(getServletContext());
        backstageController = new BackstageController(getServletContext());
        articleController = new ArticleController(getServletContext());
        licenceController = new LicenceController(getServletContext());
        loginController = new LoginController(getServletContext());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = extractAction(request);
        String view = null;
        switch (action) {
            case "identities":
                view = identityController.handle(request, response);
                break;
            case "journal":
                view = journalController.handle(request, response);
                break;
            case "backstage":
                view = backstageController.handle(request, response);
                break;
            case "article":
                view = articleController.handle(request, response);
                break;
            case "licence":
                view = licenceController.handle(request, response);
                break;
            case "login":
                view = loginController.handle(request, response);
                break;
        }
        dispatch(request, response, view);
    }


    private String extractAction(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.split("/")[2];
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String view) throws IOException, ServletException {
        if (view != null) {
            if (isRedirect(view)) {
                view = view.split(":")[1];
                if (view.contains(".jsp") || view.contains(".html") || view.contains(".htm")) {
                    view = JSPS_FILE + "/" + view;
                }
                response.sendRedirect(request.getContextPath() + view);
            } else {
                if (view.contains(".jsp") || view.contains(".html") || view.contains(".htm")) {
                    view = JSPS_FILE + "/" + view;
                }
                request.getRequestDispatcher(view).forward(request, response);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    private boolean isRedirect(String view) {
        return view.split(":")[0].equals("redirect");
    }


}
