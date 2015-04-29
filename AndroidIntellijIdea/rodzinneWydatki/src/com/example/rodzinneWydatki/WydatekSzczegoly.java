package com.example.rodzinneWydatki;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by tomek on 28.04.15.
 */
public class WydatekSzczegoly extends Activity {

    protected TextView sklep;
    protected TextView typ;
    protected int wydatekId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wydatki_szczegoly);

        wydatekId = getIntent().getIntExtra("WYDATEK_ID", 0);
        SQLiteDatabase db = (new DatabaseHelper(this)).getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT exp._id, exp.nazwa, exp.cena, exp.data, exp.sklep, exp.typ FROM wydatki exp WHERE exp._id = ?",
                new String[]{""+wydatekId});

        if (cursor.getCount() == 1)
        {
            cursor.moveToFirst();

            sklep = (TextView) findViewById(R.id.sklep);
            sklep.setText(cursor.getString(cursor.getColumnIndex("sklep")));

            typ = (TextView) findViewById(R.id.typ);
            typ.setText(cursor.getString(cursor.getColumnIndex("typ")));

        }

    }


}
