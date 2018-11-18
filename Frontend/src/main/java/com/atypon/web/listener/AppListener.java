package com.atypon.web.listener;


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

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
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