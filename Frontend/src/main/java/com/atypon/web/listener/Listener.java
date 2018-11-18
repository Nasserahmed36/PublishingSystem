package com.atypon.web.listener;

import com.atypon.domain.dao.IdentityDao;
import com.atypon.domain.dao.IdentityDaoImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener()
public class Listener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        DataSource dataSource = getDataSource();
        context.setAttribute("datasource", dataSource);

        IdentityDao identityDao = new IdentityDaoImpl(dataSource);
        //context.setAttribute("identityDao", identityDao);
    }

    public void contextDestroyed(ServletContextEvent sce) {of 

    }

    private DataSource getDataSource() {
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
