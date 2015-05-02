package com.example.rodzinneWydatki.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tomek on 30.04.15.
 */
public class WydatkiDBHepler extends SQLiteOpenHelper {

    public WydatkiDBHepler(Context context) {
        super(context, WydatkiKontrakt.DB_NAME, null, WydatkiKontrakt.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String sqlQuery =
                String.format("CREATE TABLE %s (" +
                                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "%s TEXT,  " +
                                "%s TEXT,  " +
                                "%s TEXT,  " +
                                "%s TEXT,  " +
                                "%s TEXT)", WydatkiKontrakt.TABLE,
                        WydatkiKontrakt.Columns.NAZWA_WYDATKU,
                        WydatkiKontrakt.Columns.CENA_WYDATKU,
                        WydatkiKontrakt.Columns.TYP_WYDATKU,
                        WydatkiKontrakt.Columns.NAZWA_SKLEPU,
                        WydatkiKontrakt.Columns.DATA_WYDATKU);

        Log.d("WydatkiDBHepler", "Zapytanie SQL: " + sqlQuery);
        sqlDB.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int i, int i2) {
        sqlDB.execSQL("DROP TABLE IF EXISTS "+WydatkiKontrakt.TABLE);
        onCreate(sqlDB);
    }
}
