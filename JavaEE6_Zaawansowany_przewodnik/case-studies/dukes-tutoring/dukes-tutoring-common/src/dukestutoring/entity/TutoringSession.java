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

import dukestutoring.util.CalendarUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author ian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "TutoringSession.findByDate",query = "SELECT DISTINCT t "
    + "FROM TutoringSession t " + "WHERE t.sessionDate = :date ")
})
@XmlRootElement(name = "TutoringSession")
@XmlAccessorType(XmlAccessType.FIELD)
public class TutoringSession implements Serializable {
    private static final long serialVersionUID = -7046584503641718822L;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    private Calendar sessionDate;
    @OneToMany(mappedBy = "tutoringSession", cascade = CascadeType.ALL)
    private List<StatusEntry> statusEntries;
    @XmlTransient
    @ManyToMany()
    private List<Student> students;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public TutoringSession() {
        Calendar cal = Calendar.getInstance();
        CalendarUtil.stripTime(cal);
        this.setSessionDate(cal);
        this.students = new ArrayList<Student>();
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
        if (!(object instanceof TutoringSession)) {
            return false;
        }

        TutoringSession other = (TutoringSession) object;

        if (((this.id == null) && (other.id != null))
                || ((this.id != null) && !this.id.equals(other.id))) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "dukestutoring.entity.Session[id=" + id + "]";
    }

    /**
     * @return the students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(List<Student> students) {
        this.setStudents(students);
    }

    /**
     * @return the statusEntries
     */
    public List<StatusEntry> getStatusEntries() {
        return statusEntries;
    }

    /**
     * @param statusEntries the statusEntries to set
     */
    public void setStatusEntries(List<StatusEntry> statusEntries) {
        this.statusEntries = statusEntries;
    }

    /**
     * @return the sessionDate
     */
    public Calendar getSessionDate() {
        return sessionDate;
    }

    /**
     * @param sessionDate the sessionDate to set
     */
    public void setSessionDate(Calendar sessionDate) {
        this.sessionDate = sessionDate;
    }
}
