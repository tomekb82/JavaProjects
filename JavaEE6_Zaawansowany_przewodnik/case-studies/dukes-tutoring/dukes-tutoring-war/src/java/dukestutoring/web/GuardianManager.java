/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dukestutoring.web;

import dukestutoring.ejb.AdminBean;
import dukestutoring.entity.Guardian;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;


/**
 *
 * @author ian
 */
@Named
@ConversationScoped
public class GuardianManager implements Serializable {
    private static final long serialVersionUID = -9204236755527878515L;
    private static final Logger logger = Logger.getLogger(
                "dukestutoring.web.GuardianManager");
    @EJB
    protected AdminBean adminBean;
    protected Boolean createGuardian;
    protected Guardian newGuardian;
    protected List<Guardian> allGuardians;
    protected List<Guardian> selectedGuardians;

    /**
     * Creates a new instance of GuardianManager
     */
    public GuardianManager() {
        this.newGuardian = new Guardian();
        this.selectedGuardians = new ArrayList<Guardian>();
    }

    /**
     * Get the value of createGuardian
     *
     * @return the value of createGuardian
     */
    public Boolean getCreateGuardian() {
        return createGuardian;
    }

    /**
     * Set the value of createGuardian
     *
     * @param createGuardian new value of createGuardian
     */
    public void setCreateGuardian(Boolean createGuardian) {
        this.createGuardian = createGuardian;
    }

    /**
     * Get the value of allGuardians
     *
     * @return the value of allGuardians
     */
    public List<Guardian> getAllGuardians() {
        if (allGuardians == null) {
            try {
                this.allGuardians = adminBean.getAllGuardians();
            } catch (Exception ex) {
                logger.log(
                        Level.SEVERE,
                        "adminBean.getAllGuardians returned an error{0}",
                        ex.getMessage());
            }
        }

        return this.allGuardians;
    }

    /**
     * Set the value of allGuardians
     *
     * @param allGuardians new value of allGuardians
     */
    public void setAllGuardians(List<Guardian> allGuardians) {
        this.allGuardians = allGuardians;
    }

    /**
     * Get the value of selectedGuardians
     *
     * @return the value of selectedGuardians
     */
    public List<Guardian> getSelectedGuardians() {
        return selectedGuardians;
    }

    /**
     * Set the value of selectedGuardians
     *
     * @param selectedGuardians new value of selectedGuardians
     */
    public void setSelectedGuardians(List<Guardian> selectedGuardians) {
        this.selectedGuardians = selectedGuardians;
    }

    /**
     * Get the value of newGuardian
     *
     * @return the value of newGuardian
     */
    public Guardian getNewGuardian() {
        return newGuardian;
    }

    /**
     * Set the value of newGuardian
     *
     * @param newGuardian new value of newGuardian
     */
    public void setNewGuardian(Guardian newGuardian) {
        this.newGuardian = newGuardian;
    }

    public void renderCreateForm(ActionEvent ae) {
        this.createGuardian = true;
    }
}
