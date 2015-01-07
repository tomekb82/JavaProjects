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
package dukestutoring.entity;

import dukestutoring.util.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author ian
 */
@Entity
@XmlRootElement(name = "Person")
public class Person implements Serializable {
    private static final long serialVersionUID = -538619907692526070L;
    @OneToMany
    protected List<Address> addresses;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @OneToOne
    protected PersonDetails details;
    @Email
    protected String email;
    @NotNull
    protected String firstName;
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "{invalid.phonenumber}")
    protected String homePhone;
    @NotNull
    protected String lastName;
    protected String middleName;
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "{invalid.phonenumber}")
    protected String mobilePhone;
    protected String nickname;
    @XmlTransient
    protected String password;
    protected String suffix;

    public Person() {
        this.addresses = new ArrayList<Address>();
    }

    public Person(
        String firstName,
        String lastName,
        String middleName,
        String suffix,
        String email,
        String homePhone,
        String mobilePhone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.suffix = suffix;
        this.email = email;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
    }

    public Person(
        String firstName,
        String lastName,
        String email,
        String homePhone) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.homePhone = homePhone;
    }

    public Person(
        String lastName,
        String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ((id != null) ? id.hashCode() : 0);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }

        Person other = (Person) object;

        if (((this.id == null) && (other.id != null))
                || ((this.id != null) && !this.id.equals(other.id))) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "dukestutoring.entity.Person[id=" + id + "]";
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the suffix
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * @param suffix the suffix to set
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getName() {
        if (null == this.nickname) {
            return this.getFirstName() + " " + this.getLastName();
        } else {
            return this.getNickname() + " " + this.getLastName();
        }
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the homePhone
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     * @param homePhone the homePhone to set
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    /**
     * @return the mobilePhone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone the mobilePhone to set
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * @return the addresses
     */
    public List<Address> getAddresses() {
        return addresses;
    }

    /**
     * @param addresses the addresses to set
     */
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the details of the person
     */
    public PersonDetails getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(PersonDetails details) {
        this.details = details;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
