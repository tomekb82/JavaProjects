package com.example.rodzinneWydatki;

/**
 * Created by tomek on 02.05.15.
 */
public enum TypWydatku {

    ROZRYWKA("Rozrywka"),
    DZIECKO("Dziecko"),
    DOMOWE("Domowe"),
    RACHUNKI("Rachunki"),
    SPOZYWCZE("Spożywcze"),
    TOMEK("Tomek"),
    KAMILA("Kamila"),
    INNE("Inne");

    private String value;
    TypWydatku(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
