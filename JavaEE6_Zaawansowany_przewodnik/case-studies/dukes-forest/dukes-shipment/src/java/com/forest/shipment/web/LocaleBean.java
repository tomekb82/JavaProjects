/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package com.forest.shipment.web;

import java.io.Serializable;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named(value = "localeBean")
@SessionScoped
public class LocaleBean implements Serializable {
    private static final long serialVersionUID = -5322261872682904868L;
    private Locale locale = FacesContext.getCurrentInstance()
                                        .getViewRoot()
                                        .getLocale();

    /**
     * Creates a new instance of LocaleBean
     */
    public LocaleBean() {
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance()
                    .getViewRoot()
                    .setLocale(locale);
    }
}
