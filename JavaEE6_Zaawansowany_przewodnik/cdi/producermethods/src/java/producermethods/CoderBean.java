/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package producermethods;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * Zarządzane ziarno z wstrzykiwaną implementacją Coder w celu przeprowadzenia
 * szyfrowania tekstu.
 */
@Named
@RequestScoped
public class CoderBean {
    private static final int TEST = 1;
    private static final int SHIFT = 2;
    @Inject
    @Chosen
    @RequestScoped
    Coder coder;
    private String codedString;
    private String inputString;
    private int coderType = SHIFT; // wartość domyślna
    @Max(26)
    @Min(0)
    @NotNull
    private int transVal;

    /**
     * Metoda produkująca wybierajaca implementację na podstawie wartości coderType.
     *
     * @param tci  implementacja testowwa
     * @param ci   implementacja właściwa
     * @return     wybrana implementacja
     */
    @Produces
    @Chosen
    @RequestScoped
    public Coder getCoder(
        @New
    TestCoderImpl tci,
        @New
    CoderImpl ci) {
        switch (coderType) {
        case TEST:
            return tci;

        case SHIFT:
            return ci;

        default:
            return null;
        }
    }

    public void encodeString() {
        setCodedString(coder.codeString(inputString, transVal));
    }

    public void reset() {
        setInputString("");
        setTransVal(0);
    }

    /**** metody ustawiające i pobierajace ****/
    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inString) {
        inputString = inString;
    }

    public String getCodedString() {
        return codedString;
    }

    public void setCodedString(String str) {
        codedString = str;
    }

    public int getTransVal() {
        return transVal;
    }

    public void setTransVal(int tval) {
        transVal = tval;
    }

    public int getCoderType() {
        return coderType;
    }

    public void setCoderType(int coderType) {
        this.coderType = coderType;
    }
}
