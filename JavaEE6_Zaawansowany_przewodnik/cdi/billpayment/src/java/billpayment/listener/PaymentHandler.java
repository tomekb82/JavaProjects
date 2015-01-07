/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package billpayment.listener;

import billpayment.event.PaymentEvent;
import billpayment.interceptor.Logged;
import billpayment.payment.Credit;
import billpayment.payment.Debit;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;


/**
 * Obsługa dwóch rodzajów PaymentEvent.
 */
@Logged
@SessionScoped
public class PaymentHandler implements Serializable {
    private static final Logger logger = Logger.getLogger(
                PaymentHandler.class.getCanonicalName());
    private static final long serialVersionUID = 2013564481486393525L;

    /**
     * Uwaga: Ponieważ to ziarno oznaczono jako @SessionScoped, powstanie jedna
     * instancja na sesję, PRAWDA?
     */
    public PaymentHandler() {
        logger.log(Level.INFO, "Utworzono PaymentHandler.");
    }

    /**
     * Obsługa płatności kartą kredytową.
     *
     * @param event   zdarzenie typu Credit
     */
    public void creditPayment(@Observes
    @Credit
    PaymentEvent event) {
        logger.log(
                Level.INFO,
                "PaymentHandler - karta kredytowa: {0}",
                event.toString());

        // wywołania specyficzne dla wersji Credit...
    }

    /**
     * Obsługa płatności kartą debetową.
     *
     * @param event   zdarzenie typu Debit
     */
    public void debitPayment(@Observes
    @Debit
    PaymentEvent event) {
        logger.log(
                Level.INFO,
                "PaymentHandler - karta debetowa: {0}",
                event.toString());

        // wywołania specyficzne dla wersji Debit...
    }
}
