package com.github.adminfaces.starter;

import com.github.adminfaces.template.session.AdminFilter;
import com.github.adminfaces.template.session.AdminServletContextListener;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartupListener implements ServletContextListener {

    @ConfigProperty(name = "javax.faces.PROJECT_STAGE")
    String projectStage;



    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setInitParameter("javax.faces.PROJECT_STAGE", projectStage);
        sce.getServletContext().setInitParameter("primefaces.THEME", "admin");
        sce.getServletContext().setInitParameter("primefaces.FONT_AWESOME", "true");
        sce.getServletContext().setInitParameter("javax.faces.FACELETS_RESOURCE_RESOLVER", "com.github.adminfaces.starter.CustomResourceResolver");
        try {
            sce.getServletContext().createListener(AdminServletContextListener.class);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        sce.getServletContext().addFilter("AdminFilter", AdminFilter.class);
    }

}
