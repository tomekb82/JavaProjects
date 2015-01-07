/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package dukestutoring.web.util;

import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;


/**
 *
 * @author ievans
 */
public class EntityConverter {
    private static final String key = "dukestutoring.web.util.EntityConverter";

    public EntityConverter() {
    }

    protected Map<String, Object> getViewMap(FacesContext context) {
        Map<String, Object> viewMap = context.getViewRoot()
                                             .getViewMap();
        @SuppressWarnings({
            "unchecked",
            "rawtypes"
        })
        Map<String, Object> idMap = (Map) viewMap.get(key);

        if (idMap == null) {
            idMap = new HashMap<String, Object>();
            viewMap.put(key, idMap);
        }

        return idMap;
    }
}
