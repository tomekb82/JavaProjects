package com.example.rodzinneWydatki;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tomek on 27.04.15.
 */
public class Wydatek {

    private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    private String nazwa;
    private BigDecimal cena;
    private Date data;


    public Wydatek(String nazwa, BigDecimal cena, Date data) {
        this.data = data;
        this.cena = cena;
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return nazwa.toUpperCase() + "   " + cena + " PLN   " + df.format(data);
    }
}
