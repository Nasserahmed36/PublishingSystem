package com.atypon.web.servlet;//package com.atypon.com.atypon.web.servlet;


import com.atypon.web.controller.IssueController;
import com.atypon.web.controller.JournalController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DispatcherServlet", urlPatterns = {"/journal/*","/issue/*"})
public class DispatcherServlet extends HttpServlet {
    private static final String JSPS_FILE = "/publish";

    private JournalController journalController;
    private IssueController issueController;

    @Override
    public void init() throws ServletException {
        super.init();
        journalController = new JournalController(getServletContext());
        issueController = new IssueController(getServletContext());
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
                view = issueController.handle(request,response);
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


    private boolean isRedirect(String view) {
        return view.split(":")[0].equals("redirect");
    }

}
