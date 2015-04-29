package com.example.rodzinneWydatki;

/**
 * Created by tomek on 29.04.15.
 */
public class WydatekAkcja {
    private String label;

    private String data;

    private int type;

    public static final int AKCJA_SKLEP = 1;
    public static final int AKCJA_TYP = 2;
    public static final int AKCJA_RAPORTY = 3;

    public WydatekAkcja(String label, String data, int type) {
        super();
        this.label = label;
        this.data = data;
        this.type = type;
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

}


