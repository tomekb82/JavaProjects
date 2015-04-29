package com.example.rodzinneWydatki;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tomek on 27.04.15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "wydatki";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS wydatki (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nazwa TEXT, " +
                "cena TEXT, " +
                "data TEXT, " +
                "sklep TEXT, " +
                "typ TEXT" +
                ")";
        db.execSQL(sql);

        ContentValues values = new ContentValues();

        values.put("nazwa", "pieluchy");
        values.put("cena", "23.60");
        values.put("data", "11/11/2015");
        values.put("sklep", "Sklep 1");
        values.put("typ", "Typ 1");
        db.insert("wydatki", "nazwa", values);

        values.put("nazwa", "spodnie");
        values.put("cena", "324.60");
        values.put("data", "04/12/2015");
        values.put("sklep", "Sklep 2");
        values.put("typ", "Typ 2");
        db.insert("wydatki", "nazwa", values);


        values.put("nazwa", "kurtka");
        values.put("cena", "234.60");
        values.put("data", "6/3/2015");
        values.put("sklep", "Sklep 3");
        values.put("typ", "Typ 3");
        db.insert("wydatki", "nazwa", values);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS wydatki");
        onCreate(db);
    }

}

