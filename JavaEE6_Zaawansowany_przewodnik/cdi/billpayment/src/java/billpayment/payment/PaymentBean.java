/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package billpayment.payment;

import billpayment.event.PaymentEvent;
import billpayment.interceptor.Logged;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Digits;


/**
 * Ziarno zgłaszające zdarzenia płatności DEBIT i CREDIT na podstawie wyboru
 * dokonanego w interfejsie użytkownika.
 * Sprawdź dziennik zdarzeń serwera.
 */
@Named
@SessionScoped
public class PaymentBean implements Serializable {
    private static final Logger logger = Logger.getLogger(
                PaymentBean.class.getCanonicalName());
    private static final long serialVersionUID = 7130389273118012929L;
    private static final int DEBIT = 1;
    private static final int CREDIT = 2;
    @Inject
    @Credit
    Event<PaymentEvent> creditEvent;
    @Inject
    @Debit
    Event<PaymentEvent> debitEvent;
    @Digits(integer = 10, fraction = 2, message = "Nieprawidłowa wartość")
    private BigDecimal value;
    private Date datetime;
    private int paymentOption = DEBIT;

    /**
     * Zgłasza zdarzenie płatności.
     *
     * @return lokalizacja strony odpowiedzi
     */
    @Logged
    public String pay() {
        this.setDatetime(Calendar.getInstance().getTime());

        switch (paymentOption) {
        case DEBIT:

            PaymentEvent debitPayload = new PaymentEvent();
            debitPayload.setPaymentType("Debetowa");
            debitPayload.setValue(value);
            debitPayload.setDatetime(datetime);
            debitEvent.fire(debitPayload);

            break;

        case CREDIT:

            PaymentEvent creditPayload = new PaymentEvent();
            creditPayload.setPaymentType("Kredytowa");
            creditPayload.setValue(value);
            creditPayload.setDatetime(datetime);
            creditEvent.fire(creditPayload);

            break;

        default:
            logger.severe("Niewłaściwy rodzaj płatności!");
        }

        return "/response.xhtml";
    }

    /**
     * Wyczyść wartości na strone indeksu.
     */
    @Logged
    public void reset() {
        setPaymentOption(DEBIT);
        setValue(BigDecimal.ZERO);
    }

    /**** metody pobierające i ustawiające ****/
    public int getPaymentOption() {
        return this.paymentOption;
    }

    public void setPaymentOption(int paymentType) {
        this.paymentOption = paymentType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
