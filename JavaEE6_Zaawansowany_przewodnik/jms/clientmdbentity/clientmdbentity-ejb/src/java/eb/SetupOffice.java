/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package eb;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * The SetupOffice class implements the business methods of
 * the entity.
 */
@Entity
public class SetupOffice implements Serializable {
    static final Logger logger = Logger.getLogger("SetupOffice");
    private static final long serialVersionUID = 242025229708136626L;
    private String equip;

    /*
     * There should be a list of replies for each message being
     * joined.  This example is joining the work of separate
     * departments on the same original request, so it is all
     * right to have only one reply destination.  In theory, this
     * should be a set of destinations, with one reply for each
     * unique destination.
     */
    private String id;
    private String name;
    private int officeNum;

    /**
     * no-argument constructor
     */
    public SetupOffice() {
    }

    /**
     * Constructor with two arguments
     *
     * @param newHireID  employee ID (primary key)
     * @param name       employee name
     */
    public SetupOffice(
        String newhireID,
        String name) {
        setEmployeeId(newhireID);
        setEmployeeName(name);
        setEquipmentList(null);
        setOfficeNumber(-1);
    }

    @Id
    public String getEmployeeId() {
        return id;
    }

    private void setEmployeeId(String id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return name;
    }

    private void setEmployeeName(String name) {
        this.name = name;
    }

    public int getOfficeNumber() {
        return officeNum;
    }

    private void setOfficeNumber(int officeNum) {
        this.officeNum = officeNum;
    }

    public String getEquipmentList() {
        return equip;
    }

    private void setEquipmentList(String equip) {
        this.equip = equip;
    }

    /**
     * The doEquipmentList method stores the assigned equipment
     * in the database, then determines if setup is complete.
     *
     * @param list    assigned equipment
     *
     * @return        true if setup is complete
     */
    public boolean doEquipmentList(String list) {
        boolean done;

        setEquipmentList(list);
        logger.log(
                Level.INFO,
                "SetupOffice.doEquipmentList: Sprzęt dla pracownika o "
                + "identyfikatorze {0} to {1} (numer biura: {2})",
                new Object[] {
                    getEmployeeId(), getEquipmentList(), getOfficeNumber()
                });
        done = checkIfSetupComplete();

        return done;
    }

    /**
     * The doOfficeNumber method stores the assigned office
     * number in the database, then determines if setup is complete.
     *
     * @param officeNum    assigned office
     *
     * @return        true if setup is complete
     */
    public boolean doOfficeNumber(int officeNum) {
        boolean done;

        setOfficeNumber(officeNum);
        logger.log(
                Level.INFO,
                "SetupOffice.doOfficeNumber: Numer biur dla pracownika o "
                + "identyfikatorze {0} to {1} (sprzęt: {2})",
                new Object[] {
                    getEmployeeId(), getOfficeNumber(), getEquipmentList()
                });
        done = checkIfSetupComplete();

        return done;
    }

    /**
     * The checkIfSetupComplete method determines whether
     * both the office and the equipment have been assigned.  If
     * so, it reports that the work of the entity is done.
     *
     * @return        true if setup is complete
     */
    private boolean checkIfSetupComplete() {
        boolean allDone = false;

        if ((getEquipmentList() != null) && (getOfficeNumber() != -1)) {
            logger.log(
                    Level.INFO,
                    "SetupOffice.checkIfSetupComplete: HARMONOGRAM"
                    + " employeeId={0}, Name={1} ma pracować w biurze #{2} ze sprzętem {3}",
                    new Object[] {
                        getEmployeeId(), getEmployeeName(), getOfficeNumber(),
                        getEquipmentList()
                    });

            allDone = true;
        }

        return allDone;
    }
}
