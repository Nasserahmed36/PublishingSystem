package com.atypon.web.listener;


import com.atypon.acs.AuthenticationService;
import com.atypon.context.ApplicationContext;
import com.atypon.domain.UserRequest;
import com.atypon.domain.dao.*;
import com.atypon.notification.NotificationService;
import com.atypon.notification.NotificationServiceImpl;
import com.atypon.service.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class AppListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        DataSource dataSource = getDataSource(context);
        context.setAttribute("datasource", dataSource);
        ApplicationContext.setAttribute("dataSource", dataSource);

        JournalDao journalDao = new JournalDaoImpl(dataSource);
        context.setAttribute("journalDao", journalDao);

        JournalService journalService = new JournalServiceImpl(journalDao);
        context.setAttribute("journalService", journalService);

        IssueDao issueDao = new IssueDaoImpl(dataSource);
        context.setAttribute("issueDao", issueDao);

        IssueService issueService = new IssueServiceImpl(issueDao);
        context.setAttribute("issueService", issueService);
        ApplicationContext.setAttribute("issueService", issueService);

        ArticleDao articleDao = new ArticleDaoImpl(dataSource);
        context.setAttribute("articleDao", articleDao);

        ArticleService articleService = new ArticleServiceImpl(articleDao);
        context.setAttribute("articleService", articleService);
        ApplicationContext.setAttribute("articleService", articleService);


        NotificationService notificationService = new NotificationServiceImpl(new NotificationDaoImpl(dataSource));
        context.setAttribute("notificationService", notificationService);
        ApplicationContext.setAttribute("notificationService", notificationService);

        IdentityDao identityDao = new IdentityDaoImpl(dataSource);
        context.setAttribute("identityDao", identityDao);

        IdentityService identityService = new IdentityServiceImpl(identityDao);
        context.setAttribute("identityService", identityService);

        context.setAttribute("authenticationService", (AuthenticationService) (userRequest, user, contentId) -> true);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private DataSource getDataSource(ServletContext servletContext) {
        DataSource ds;
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:/comp/env/" + "jdbc/mysql");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return ds;
    }
}