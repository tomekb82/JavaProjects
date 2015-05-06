package com.example.rodzinneWydatki;

/**
 * Created by tomek on 29.04.15.
 */
public class WydatekAkcja {
    private String label;
    private String data;

    private int type;
    private int color;

    public static final int AKCJA_SKLEP = 1;
    public static final int AKCJA_TYP = 2;
    public static final int AKCJA_RAPORTY = 3;

    public static final int AKCJA_RAPORT_STYCZEN = 999;
    public static final int AKCJA_RAPORT_LUTY = 998;
    public static final int AKCJA_RAPORT_MARZEC = 997;
    public static final int AKCJA_RAPORT_KWIECIEN = 996;
    public static final int AKCJA_RAPORT_MAJ = 995;
    public static final int AKCJA_RAPORT_CZERWIEC = 994;

    public static final int AKCJA_RAPORT_ROZRYWKA = 993;
    public static final int AKCJA_RAPORT_DZIECKO = 992;
    public static final int AKCJA_RAPORT_DOMOWE = 991;;
    public static final int AKCJA_RAPORT_RACHUNKI = 990;
    public static final int AKCJA_RAPORT_SPOZYWCZE = 989;
    public static final int AKCJA_RAPORT_TOMEK = 988;
    public static final int AKCJA_RAPORT_KAMILA = 987;
    public static final int AKCJA_RAPORT_INNE = 986;

    public WydatekAkcja(String label, String data, int type, int color) {
        super();
        this.label = label;
        this.data = data;
        this.type = type;
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}


