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

import dukestutoring.entity.Address;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


/**
 *
 * @author ian
 */
@Named
@RequestScoped
public class AddressManager {
    protected Address newAddress;

    /** Creates a new instance of AddressManager */
    public AddressManager() {
        this.newAddress = new Address();
    }

    /**
     * Get the value of newAddress
     *
     * @return the value of newAddress
     */
    public Address getNewAddress() {
        return newAddress;
    }

    /**
     * Set the value of newAddress
     *
     * @param newAddress new value of newAddress
     */
    public void setNewAddress(Address newAddress) {
        this.newAddress = newAddress;
    }
}
