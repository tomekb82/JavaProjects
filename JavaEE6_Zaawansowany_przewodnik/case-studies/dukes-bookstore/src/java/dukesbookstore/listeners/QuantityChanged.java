/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package dukesbookstore.listeners;

import dukesbookstore.web.managedbeans.ShowCartBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;


/**
 *
 * <p>Value change listener for the quantity entered on the
 * <code>bookshowcart.xhtml</code> page.</p>
 */
public class QuantityChanged extends Object implements ValueChangeListener {
    private static final Logger logger = Logger.getLogger(
                "dukesbookstore.listeners.QuantityChanged");

    @java.lang.Override
    public void processValueChange(ValueChangeEvent event)
        throws AbortProcessingException {
        logger.log(Level.INFO, "Entering QuantityChanged.processValueChange");

        if (null != event.getNewValue()) {
            FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getSessionMap()
                        .put("changed", "true");
        } else {
            FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getSessionMap()
                        .put("changed", "false");
        }
    }
}
