package com.github.adminfaces.starter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartupListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setInitParameter("primefaces.THEME", "admin");
        sce.getServletContext().setInitParameter("primefaces.FONT_AWESOME", "true");
    }

}
