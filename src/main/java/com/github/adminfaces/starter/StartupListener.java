package com.github.adminfaces.starter;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.EnumSet;

import static com.github.adminfaces.template.util.Assert.has;

@WebListener
public class StartupListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setInitParameter("primefaces.THEME", "admin");
        sce.getServletContext().setInitParameter("primefaces.FONT_AWESOME", "true");

        String viewsInSession = has(System.getenv("ViewsInSession")) ? System.getenv("ViewsInSession") : "5";
        sce.getServletContext().setInitParameter("com.sun.faces.numberOfLogicalViews",viewsInSession) ;
        sce.getServletContext().setInitParameter("com.sun.faces.numberOfViewsInSession", viewsInSession);
        sce.getServletContext().setInitParameter("org.omnifaces.VIEW_SCOPE_MANAGER_MAX_ACTIVE_VIEW_SCOPES", viewsInSession);

        FilterRegistration.Dynamic gzipResponseFilter = sce.getServletContext().addFilter("gzipResponseFilter", "org.omnifaces.filter.GzipResponseFilter");
        gzipResponseFilter.setInitParameter("threshold", "200");
        gzipResponseFilter.addMappingForServletNames(EnumSet.of(DispatcherType.ERROR), true, "Faces Servlet");
        sce.getServletContext().setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
    }

}
