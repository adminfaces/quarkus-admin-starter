package com.github.adminfaces.starter;

import org.apache.myfaces.view.facelets.impl.DefaultResourceResolver;

import java.net.URL;

/**
 * see issue: https://github.com/tandraschko/quarkus-myfaces/issues/3
 */
public class CustomResourceResolver extends DefaultResourceResolver {

    @Override
    public URL resolveUrl(String resource) {
        URL resourceUrl = super.resolveUrl(resource);
        if (resourceUrl == null) {
            if (resource.equals("/")) {
                resource = "/index.xhtml";
            }
            resourceUrl = super.resolveUrl(resource);
        }
        return resourceUrl;
    }
}