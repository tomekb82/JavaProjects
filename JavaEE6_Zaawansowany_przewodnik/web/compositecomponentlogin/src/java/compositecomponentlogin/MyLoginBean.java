/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package compositecomponentlogin;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@RequestScoped
public class MyLoginBean {
    private String name;
    private String password;

    /** Tworzy nową instancję MyLoginBean */
    public MyLoginBean() {
    }

    public MyLoginBean(
        String name,
        String password) {
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newValue) {
        password = newValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String newValue) {
        name = newValue;
    }

    public String login() {
        if (getName()
                    .equals("javaee")) {
            String msg = "Sukces. Twoja nazwa użytkownika to " + getName()
                + ", a Twoje hasło to " + getPassword();
            FacesMessage facesMsg = new FacesMessage(msg, msg);
            FacesContext.getCurrentInstance()
                        .addMessage(null, facesMsg);

            return "index";
        } else {
            String msg = "Porażka. Twoja nazwa użytkownika to " + getName()
                + ", a Twoje hasło to " + getPassword();
            FacesMessage facesMsg = new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        msg,
                        msg);
            FacesContext.getCurrentInstance()
                        .addMessage(null, facesMsg);

            return "index";
        }
    }
}
