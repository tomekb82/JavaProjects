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

import dukestutoring.entity.Student;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import static org.junit.Assert.*;
import org.junit.*;


/**
 *
 * @author ian
 */
public class RequestBeanTest {
    static EJBContainer ec;
    static Context ctx;
    static RequestBean requestBean;
    static AdminBean adminBean;

    // students
    static Student maeby;
    static Student georgeMichael;
    static Student gob;

    public RequestBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ec = EJBContainer.createEJBContainer();
        ctx = ec.getContext();

        requestBean = (RequestBean) ctx.lookup(
                    "java:global/classes/RequestBean");
        adminBean = (AdminBean) ctx.lookup("java:global/classes/AdminBean");
        createStudents();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        ec.close();
    }

    @Before
    public void setUp() throws NamingException {
        this.checkInStudents();
    }

    @After
    public void tearDown() throws NamingException {
        this.checkOutStudents();
    }

    /**
     * Test of checkIn method, of class RequestBean.
     */
    @Test
    public void testCheckIn() throws Exception {
        System.out.println("checkIn");
        requestBean.checkIn(gob);
        this.checkStatus();

        List<Student> checkedInStudents = requestBean.getCheckedInStudents();
        assertTrue(
                "Checked in students doesn't contain Gob!",
                checkedInStudents.contains(gob));
    }

    /**
     * Test of checkOut method, of class RequestBean.
     */
    @Test
    public void testCheckOut() throws Exception {
        System.out.println("checkOut");

        requestBean.checkOut(maeby);

        // TODO review the generated test code and remove the default call to fail.
        List<Student> checkedInStudents = requestBean.getCheckedInStudents();
        this.checkStatus();
        assertFalse(
                "Checked in students contains Maeby!",
                checkedInStudents.contains(maeby));
    }

    /**
     * Test of atPark method, of class RequestBean.
     */
    @Test
    public void testAtPark() throws Exception {
        System.out.println("atPark");
        requestBean.atPark(georgeMichael);

        List<Student> atParkStudents = requestBean.getStudentsAtPark();
        assertTrue(
                "George Michael not at park!",
                atParkStudents.contains(georgeMichael));
    }

    /**
     * Test of backFromPark method, of class RequestBean.
     */
    @Test
    public void testBackFromPark() throws Exception {
        System.out.println("backFromPark");
        requestBean.backFromPark(georgeMichael);

        List<Student> inStudents = requestBean.getCheckedInStudents();
        assertTrue(
                "George Michael not in after going to park!",
                inStudents.contains(georgeMichael));

        List<Student> atParkStudents = requestBean.getStudentsAtPark();
        assertFalse(
                "George Michael still at the park!",
                atParkStudents.contains(georgeMichael));
    }

    /**
     * Test of createStudent method, of class RequestBean.
     */
    @Test
    public void testCreateStudent() throws Exception {
        System.out.println("createStudent");

        // create Buster
        Student buster = new Student();
        buster.setLastName("Bluth");
        buster.setFirstName("Byron");
        buster.setNickname("Buster");
        buster.setGrade(6);
        buster.setSchool("Milford Academy");
        adminBean.createStudent(buster);

        List<Student> allStudents = requestBean.getAllStudents();
        assertTrue(
            "Buster not created!",
            allStudents.contains(buster));
    }

    /**
     * Test of createStudents method, of class RequestBean.
     */
    @Test
    public void testCreateStudents() throws Exception {
        System.out.println("createStudents");

        // create Buster
        Student buster = new Student();
        buster.setLastName("Bluth");
        buster.setFirstName("Byron");
        buster.setNickname("Buster");
        buster.setGrade(6);
        buster.setSchool("Milford Academy");

        // create Ann
        Student ann = new Student();
        ann.setLastName("Veal");
        ann.setFirstName("Ann");
        ann.setGrade(10);
        ann.setSchool("Huntington Beach High School");

        List<Student> newStudents = new ArrayList<Student>();
        newStudents.add(buster);
        newStudents.add(ann);
        adminBean.createStudents(newStudents);

        List<Student> allStudents = requestBean.getAllStudents();
        assertTrue(
            "Buster not created!",
            allStudents.contains(buster));
        assertTrue(
            "Ann not created!",
            allStudents.contains(ann));
    }

    /**
     * Test of removeStudent method, of class RequestBean.
     */
    @Test
    public void testRemoveStudent() throws Exception {
        System.out.println("removeStudent");
        adminBean.removeStudent(maeby);

        List<Student> allStudents = requestBean.getAllStudents();
        assertFalse(
            "Maeby wasn't removed!",
            allStudents.contains(maeby));
    }

    /**
     * Test of removeStudents method, of class RequestBean.
     */
    @Test
    public void testRemoveStudents() throws Exception {
        System.out.println("removeStudent");

        List<Student> droppedStudents = new ArrayList<Student>();
        droppedStudents.add(maeby);
        droppedStudents.add(gob);
        adminBean.removeStudents(droppedStudents);

        List<Student> allStudents = requestBean.getAllStudents();
        assertFalse(
            "Maeby wasn't removed!",
            allStudents.contains(maeby));
        assertFalse(
            "Gob wasn't removed!",
            allStudents.contains(gob));
    }

    /**
     * Test of getAllStudents method, of class RequestBean.
     */
    @Test
    public void testGetAllStudents() throws Exception {
        System.out.println("getAllStudents");

        List<Student> allStudents = requestBean.getAllStudents();
        assertFalse(
            "No students returned!",
            allStudents.isEmpty());
        assertEquals(
                "All students not the right size! getAllStudents returned: "
                + allStudents.size(),
                allStudents.size(),
                3);
    }

    /**
     * Test of getStudentsInPlayground method, of class RequestBean.
     */
    @Test
    public void testGetStudentsInPlayground() throws Exception {
        System.out.println("getStudentsInPlayground");

        List<Student> expResult = new ArrayList<Student>();
        requestBean.atPark(maeby);
        expResult.add(maeby);
        requestBean.atPark(georgeMichael);
        expResult.add(georgeMichael);

        List<Student> result = requestBean.getStudentsAtPark();
        assertEquals("Students in playground don't match!", expResult, result);
    }

    /**
     * Test of getCheckedInStudents method, of class RequestBean.
     */
    @Test
    public void testGetCheckedInStudents() throws Exception {
        System.out.println("getCheckedInStudents");

        List<Student> expResult = new ArrayList<Student>();
        expResult.add(maeby);
        expResult.add(georgeMichael);

        List<Student> result = requestBean.getCheckedInStudents();
        assertEquals("Checked in students don't match!", expResult, result);
    }

    /**
     * Test of getCheckedOutStudents method, of class RequestBean.
     */
    @Test
    public void testGetCheckedOutStudents() throws Exception {
        System.out.println("getCheckedOutStudents");

        List<Student> expResult = new ArrayList<Student>();
        expResult.add(gob);

        List<Student> result = requestBean.getCheckedOutStudents();
        assertEquals("Checked out students don't match!", expResult, result);
    }

    private static void createStudents() throws NamingException {
        List<Student> students = requestBean.getAllStudents();

        if (students.isEmpty()) {
            // create Maeby
            System.out.println("Creating Maeby entity");
            maeby = new Student();
            maeby.setLastName("Fünke");
            maeby.setFirstName("Maeby");
            maeby.setGrade(10);
            maeby.setSchool("Sunshine Academy");

            // create George Michael
            System.out.println("Creating George Michael entity");
            georgeMichael = new Student();
            georgeMichael.setLastName("Bluth");
            georgeMichael.setFirstName("George");
            georgeMichael.setMiddleName("Michael");
            georgeMichael.setGrade(10);
            georgeMichael.setSchool("Huntington Beach High School");

            // create GOB
            System.out.println("Creating Gob entity");
            gob = new Student();
            gob.setLastName("Bluth");
            gob.setFirstName("George");
            gob.setMiddleName("Oscar");
            gob.setNickname("Gob");
            gob.setGrade(12);
            gob.setSchool("Magician's Alliance Institute");

            System.out.println("Calling createStudent() on Maeby");
            adminBean.createStudent(maeby);
            System.out.println("Calling createStudent() on George Michael");
            adminBean.createStudent(georgeMichael);
            System.out.println("Calling createStudent() on Gob");
            adminBean.createStudent(gob);
        }
    }

    private void checkInStudents() throws NamingException {
        System.out.println("Checking in Maeby");
        requestBean.checkIn(maeby);
        System.out.println("Checking in George Michael");
        requestBean.checkIn(georgeMichael);
        this.checkStatus();
    }

    private void checkOutStudents() throws NamingException {
        System.out.println("Checking out all students");
        requestBean.checkOutAllStudents();
        this.checkStatus();
    }

    private void removeStudents() throws NamingException {
        List<Student> students = new ArrayList<Student>();
        students.add(maeby);
        students.add(georgeMichael);
        students.add(gob);
        System.out.println(
                "Calling removeStudents() on Maeby, George Michael, and Gob");
        adminBean.removeStudents(students);
    }

    private void checkStatus() throws NamingException {
        // debug
        System.out.println("Getting all students and checking status");

        List<Student> allStudents = requestBean.getAllStudents();
        Iterator<Student> i = allStudents.iterator();

        while (i.hasNext()) {
            Student student = i.next();
            System.out.println(
                    student.getName() + " is " + student.getStatus());
        }

        System.out.println(
                "Getting all checked in students and checking status");

        List<Student> checkedInStudents = requestBean.getCheckedInStudents();
        Iterator<Student> j = checkedInStudents.iterator();

        while (j.hasNext()) {
            Student student = j.next();
            System.out.println(
                    student.getName() + " is " + student.getStatus());
        }

        System.out.println(
                "Getting all checked in students and checking status");

        List<Student> checkedOutStudents = requestBean.getCheckedInStudents();
        Iterator<Student> k = checkedOutStudents.iterator();

        while (k.hasNext()) {
            Student student = k.next();
            System.out.println(
                    student.getName() + " is " + student.getStatus());
        }

        System.out.println("Getting all students at park and checking status");

        List<Student> parkStudents = requestBean.getStudentsAtPark();
        Iterator<Student> l = parkStudents.iterator();

        while (l.hasNext()) {
            Student student = l.next();
            System.out.println(
                    student.getName() + " is " + student.getStatus());
        }
    }
}
