package com.example.rodzinneWydatki.db;

import android.provider.BaseColumns;

/**
 * Created by tomek on 30.04.15.
 */
public class WydatkiKontrakt {

    public static final String DB_NAME = "com.example.Wydatki.db.wydatki";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "wydatki";

    public class Columns {
        public static final String NAZWA_WYDATKU = "nazwa";
        public static final String CENA_WYDATKU = "cena";
        public static final String TYP_WYDATKU = "typ";
        public static final String NAZWA_SKLEPU = "sklep";
        public static final String DATA_WYDATKU = "data";
        public static final String _ID = BaseColumns._ID;
    }
}
