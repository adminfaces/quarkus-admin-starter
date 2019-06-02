package com.github.adminfaces.starter;

import com.github.adminfaces.template.session.AdminFilter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartupListener implements ServletContextListener {

    @ConfigProperty(name = "javax.faces.PROJECT_STAGE")
    String projectStage;



    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setInitParameter("javax.faces.PROJECT_STAGE", "Production");
        sce.getServletContext().setInitParameter("primefaces.THEME", "admin");
        sce.getServletContext().setInitParameter("primefaces.FONT_AWESOME", "true");
        sce.getServletContext().setInitParameter("javax.faces.FACELETS_RESOURCE_RESOLVER", "com.github.adminfaces.starter.CustomResourceResolver");
        sce.getServletContext().addFilter("AdminFilter", AdminFilter.class);
    }

}
