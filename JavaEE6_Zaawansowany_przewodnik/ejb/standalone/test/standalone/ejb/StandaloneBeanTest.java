/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package standalone.ejb;

import javax.naming.Context;
import javax.ejb.embeddable.EJBContainer;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ian
 */
public class StandaloneBeanTest {
    private static final Logger logger = Logger.getLogger("standalone.ejb");
    private Context ctx;
    private EJBContainer ec;

    public StandaloneBeanTest() {
    }

    @Before
    public void setUp() {
        ec = EJBContainer.createEJBContainer();
        ctx = ec.getContext();
    }

    @After
    public void tearDown() {
        if (ec != null) {
            ec.close();
        }
    }

    /**
     * Test metody returnMessage klasy StandaloneBean.
     */
    @Test
    public void testReturnMessage() throws Exception {
        logger.info("Testowanie standalone.ejb.StandaloneBean.returnMessage()");

        StandaloneBean instance = (StandaloneBean) ctx.lookup(
                    "java:global/classes/StandaloneBean");
        String expResult = "Witamy!";
        String result = instance.returnMessage();
        assertEquals(expResult, result);
    }
}
