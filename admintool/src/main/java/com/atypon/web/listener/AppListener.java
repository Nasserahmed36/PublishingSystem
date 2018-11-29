package com.atypon.web.listener;


import com.atypon.backstage.AsynchronousService;
import com.atypon.backstage.BackstageConsumer;
import com.atypon.context.ApplicationContext;
import com.atypon.domain.ArticleSubmission;
import com.atypon.domain.dao.*;
import com.atypon.notification.NotificationService;
import com.atypon.notification.NotificationServiceImpl;
import com.atypon.service.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
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

    private AsynchronousService asynchronusService;


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        DataSource dataSource = getDataSource(context);
        context.setAttribute("datasource", dataSource);

        IdentityDao identityDao = new IdentityDaoImpl(dataSource);
        context.setAttribute("identityDao", identityDao);
        IdentityService identityService = new IdentityServiceImpl(identityDao);
        context.setAttribute("identityService", identityService);

        JournalDao journalDao = new JournalDaoImpl(dataSource);
        context.setAttribute("journalDao", journalDao);

        JournalService journalService = new JournalServiceImpl(journalDao);
        context.setAttribute("journalService", journalService);

        String articleSubmissionsPath = context.getInitParameter("articleSubmissionsPath");
        ArticleSubmissionDao articleSubmissionDao = new ArticleSubmissionDaoImpl(dataSource, articleSubmissionsPath);
        context.setAttribute("articleSubmissionDao", articleSubmissionDao);

        int queueSize = Integer.parseInt(sce.getServletContext().getInitParameter("submissionsQueueSize"));
        BlockingQueue<ArticleSubmission> creationQueue = new ArrayBlockingQueue<>(queueSize);
        BlockingQueue<ArticleSubmission> deletionQueue = new ArrayBlockingQueue<>(queueSize);


        ArticleSubmissionService articleSubmissionService = new ArticleSubmissionServiceImpl(articleSubmissionDao);
        ApplicationContext.setAttribute("articleSubmissionService", articleSubmissionService);
        ArticleSubmissionService articleSubmissionService1 = new ArticleSubmissionsProducer(articleSubmissionService,
                creationQueue, deletionQueue);
        context.setAttribute("articleSubmissionService", articleSubmissionService1);

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

        LicenceDao licenceDao = new LicenceDaoImpl(dataSource);
        context.setAttribute("licenceDao", licenceDao);

        LicenceService licenceService = new LicenceServiceImpl(licenceDao);
        context.setAttribute("licenceService", licenceService);

        ContentLicenceDao contentLicenceDao = new ContentLicenceDaoImpl(dataSource);
        context.setAttribute("contentLicenceDao", contentLicenceDao);

        ContentLicenceService contentLicenceService = new ContentLicenceServiceImpl(contentLicenceDao);
        context.setAttribute("contentLicenceService", contentLicenceService);

        NotificationDao notificationDao = new NotificationDaoImpl(dataSource);
        context.setAttribute("notificationDao", notificationDao);

        NotificationService notificationService = new NotificationServiceImpl(notificationDao);
        context.setAttribute("notificationService", notificationService);
        ApplicationContext.setAttribute("notificationService", notificationService);

        asynchronusService = new BackstageConsumer(creationQueue, deletionQueue);
        asynchronusService.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        asynchronusService.stop();
    }

    private DataSource getDataSource(ServletContext servletContext) {
        DataSource ds;
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:/comp/env/" +
                    servletContext.getInitParameter("datasourceEnv"));
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return ds;
    }
}