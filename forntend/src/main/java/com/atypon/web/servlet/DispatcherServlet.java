package com.atypon.web.servlet;//package com.atypon.com.atypon.web.servlet;


import com.atypon.web.controller.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DispatcherServlet", urlPatterns = {"/journal/*", "/issue/*", "/article/*", "/home/*", "/log/*"})
public class DispatcherServlet extends HttpServlet {
    private static final String JSPS_FILE = "/publish";

    private JournalController journalController;
    private IssueController issueController;
    private ArticleController articleController;
    private HomeController homeController;
    private LoginController loginController;

    @Override
    public void init() throws ServletException {
        super.init();
        journalController = new JournalController(getServletContext());
        issueController = new IssueController(getServletContext());
        articleController = new ArticleController(getServletContext());
        homeController = new HomeController(getServletContext());
        loginController = new LoginController(getServletContext());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = extractAction(request);
        String view = null;
        switch (action) {
            case "journal":
                view = journalController.handle(request, response);
                break;
            case "issue":
                view = issueController.handle(request, response);
                break;
            case "article":
                view = articleController.handle(request, response);
                break;
            case "home":
                view = homeController.handle(request, response);
                break;
            case "log":
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
                view = removeRedirectWord(view);
                if (view.contains(".jsp") || view.contains(".html") || view.contains(".htm")) {
                    view = JSPS_FILE + "/" + view;
                }
                response.sendRedirect(request.getContextPath() + "/" + view);
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

    private String removeRedirectWord(String view) {
        return view.substring(view.indexOf(":") + 1);
    }


    private boolean isRedirect(String view) {
        return view.split(":")[0].equals("redirect");
    }

}
