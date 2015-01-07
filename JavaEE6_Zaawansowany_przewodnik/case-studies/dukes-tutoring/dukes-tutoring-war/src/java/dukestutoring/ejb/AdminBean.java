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
package dukestutoring.ejb;

import dukestutoring.entity.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Path;


/**
 *
 * @author ian
 */
@Path("/tutoring/admin")
@Stateless
@Named
public class AdminBean {
    private static final Logger logger = Logger.getLogger(
                "dukestutoring.ejb.AdminBean");
    private CriteriaBuilder cb;
    @PersistenceContext
    private EntityManager em;
    private String username;

    @PostConstruct
    private void init() {
        cb = em.getCriteriaBuilder();
    }

    public String createStudent(Student student) {
        em.persist(student);

        return "createdStudent";
    }

    public void createStudents(List<Student> students) {
        Iterator<Student> i = students.iterator();

        while (i.hasNext()) {
            this.createStudent(i.next());
        }
    }

    public String editStudent(Student student) {
        em.merge(student);

        return "editedStudent";
    }

    public String editStudents(List<Student> students) {
        Iterator<Student> i = students.iterator();

        while (i.hasNext()) {
            this.editStudent(i.next());
        }

        return "editedStudents";
    }

    public String removeStudent(Student student) {
        student.setActive(false);
        em.merge(student);

        return "removedStudent";
    }

    public String removeStudents(List<Student> students) {
        Iterator<Student> i = students.iterator();

        while (i.hasNext()) {
            this.removeStudent(i.next());
        }

        return "removedStudents";
    }

    public String createGuardian(
        Guardian guardian,
        Student student) {
        logger.log(
                Level.INFO,
                "Creating guardian {0} for {1}",
                new Object[] { guardian.getName(), student.getName() });
        student.getGuardians()
               .add(guardian);
        guardian.getStudents()
                .add(student);
        em.merge(student);
        em.persist(guardian);

        return "createdGuardian";
    }

    public String createGuardianWithList(
        Guardian guardian,
        List<Student> students) {
        Iterator<Student> i = students.iterator();

        while (i.hasNext()) {
            Student student = i.next();
            student.getGuardians()
                   .add(guardian);
            guardian.getStudents()
                    .add(student);
            em.merge(student);
        }

        em.persist(guardian);

        return "createdGuardian";
    }

    public String editGuardian(Guardian guardian) {
        em.merge(guardian);

        return "editedGuardian";
    }

    public String editGuardians(List<Guardian> guardians) {
        Iterator<Guardian> i = guardians.iterator();

        while (i.hasNext()) {
            this.editGuardian(i.next());
        }

        return "editedGuardians";
    }

    public String removeGuardian(Guardian guardian) {
        guardian.setActive(false);

        List<Student> students = guardian.getStudents();
        Iterator<Student> i = students.iterator();

        while (i.hasNext()) {
            Student student = i.next();
            student.getGuardians()
                   .remove(guardian);
            em.merge(student);
        }

        em.merge(guardian);

        return "removedGuardian";
    }

    public String removeGuardians(List<Guardian> guardians) {
        Iterator<Guardian> i = guardians.iterator();

        while (i.hasNext()) {
            this.removeGuardian(i.next());
        }

        return "removedGuardians";
    }

    public String addGuardiansToStudent(
        List<Guardian> guardians,
        Student student) {
        String result;
        Iterator<Guardian> i = guardians.iterator();

        while (i.hasNext()) {
            Guardian guardian = i.next();
            student.getGuardians()
                   .add(guardian);
            guardian.getStudents()
                    .add(student);
            em.merge(guardian);
        }

        em.merge(student);
        result = "addedGuardians";

        return result;
    }

    public String createAddress(
        Address address,
        Person person) {
        person.getAddresses()
              .add(address);
        address.setPerson(person);
        em.merge(person);
        em.persist(address);

        return "createdAddress";
    }

    public String editAddress(Address address) {
        em.merge(address);

        return "editedAddress";
    }

    public String editAddresses(List<Address> addresses) {
        Iterator<Address> i = addresses.iterator();

        while (i.hasNext()) {
            this.editAddress(i.next());
        }

        return "editedAddresses";
    }

    public String removeAddress(Address address) {
        address.setActive(false);

        Person person = address.getPerson();
        person.getAddresses()
              .remove(address);
        em.merge(person);
        em.merge(address);

        return "removedAddress";
    }

    public String removeAddresses(List<Address> addresses) {
        Iterator<Address> i = addresses.iterator();

        while (i.hasNext()) {
            this.removeAddress(i.next());
        }

        return "removedAddresses";
    }

    public List<Guardian> getAllGuardians() {
        CriteriaQuery<Guardian> cq = em.getCriteriaBuilder()
                                       .createQuery(Guardian.class);
        Root<Guardian> guardian = cq.from(Guardian.class);
        cq.select(guardian);
        cq.distinct(true);

        TypedQuery<Guardian> q = em.createQuery(cq);

        return q.getResultList();
    }

    public List<Address> getAllAddresses() {
        CriteriaQuery<Address> cq = em.getCriteriaBuilder()
                                      .createQuery(Address.class);
        Root<Address> address = cq.from(Address.class);
        cq.select(address);
        cq.where(cb.isTrue(address.get(Address_.active)));
        cq.distinct(true);

        TypedQuery<Address> q = em.createQuery(cq);

        return q.getResultList();
    }

    public List<Student> getAllInactiveStudents() {
        CriteriaQuery<Student> cq = em.getCriteriaBuilder()
                                      .createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);
        cq.select(student);
        cq.where(cb.isFalse(student.get(Student_.active)));
        cq.distinct(true);

        TypedQuery<Student> q = em.createQuery(cq);

        return q.getResultList();
    }

    public String activateStudent(Student student) {
        student.setActive(true);
        em.merge(student);

        return "activatedStudent";
    }

    public Student findStudentById(Long id) {
        logger.log(Level.INFO, "Finding student with ID: {0}", id);

        CriteriaQuery<Student> cq = em.getCriteriaBuilder()
                                      .createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);
        cq.select(student);
        cq.where(cb.isTrue(student.get(Student_.active)));
        cq.where(
                cb.equal(
                    student.get(Student_.id),
                    id));
        cq.distinct(true);

        TypedQuery<Student> q = em.createQuery(cq);

        return q.getSingleResult();
    }

    public Guardian findGuardianById(Long id) {
        logger.log(Level.INFO, "Finding Guardian with ID: {0}", id);

        return (Guardian) em.find(Guardian.class, id);
    }

    public String createAdministrator(Administrator admin) {
        em.persist(admin);

        return "createdAdministrator";
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return FacesContext.getCurrentInstance()
                           .getExternalContext()
                           .getUserPrincipal()
                           .getName();
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance()
                           .getExternalContext()
                           .isUserInRole("Administrator");
    }

    public String logout() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance()
                                         .getExternalContext();
        ec.invalidateSession();

        return "../index.xhtml?faces-redirect=true";
    }
}
