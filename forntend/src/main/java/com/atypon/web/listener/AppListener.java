package com.atypon.web.listener;


import com.atypon.backstage.BackstageConsumer;
import com.atypon.backstage.BackstageConsumerImpl;
import com.atypon.context.ApplicationContext;
import com.atypon.domain.ArticleSubmission;
import com.atypon.domain.dao.*;
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

    private BackstageConsumer backstageConsumer;


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        DataSource dataSource = getDataSource(context);
        context.setAttribute("datasource", dataSource);


        JournalDao journalDao = new JournalDaoImpl(dataSource);
        context.setAttribute("journalDao", journalDao);

        JournalService journalService = new JournalServiceImpl(journalDao);
        context.setAttribute("journalService", journalService);

        IssueDao issueDao = new IssueDaoImpl(dataSource);
        context.setAttribute("issueDao", issueDao);

        IssueService issueService = new IssueServiceImpl(issueDao);
        context.setAttribute("issueService",issueService);

        ArticleDao articleDao = new ArticleDaoImpl(dataSource);
        context.setAttribute("articleDao",articleDao);

        ArticleService articleService = new ArticleServiceImpl(articleDao);
        context.setAttribute("articleService", articleService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        backstageConsumer.stop();
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