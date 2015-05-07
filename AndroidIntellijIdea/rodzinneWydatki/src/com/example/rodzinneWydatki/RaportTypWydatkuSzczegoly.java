package com.example.rodzinneWydatki;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.example.rodzinneWydatki.db.WydatkiDBHepler;
import com.example.rodzinneWydatki.db.WydatkiKontrakt;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomek on 03.05.15.
 */
public class RaportTypWydatkuSzczegoly extends ListMenuActivity {


    private int miesiac;
    private String typWydatku;
    private SimpleCursorAdapter listAdapter;
    protected int sumaWydatkow;
    protected TextView sumaWydatkowText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        miesiac = getIntent().getIntExtra("MIESIAC", 0);
        typWydatku = getIntent().getStringExtra("TYP_WYDATKU");

        TextView tytulText = (TextView) findViewById(R.id.title);
        tytulText.setText("Raport '" + typWydatku + "' w " + new DateFormatSymbols().getMonths()[miesiac-1]);

        SQLiteDatabase db = (new WydatkiDBHepler(this)).getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT exp._id, exp.nazwa, exp.cena, exp.data, exp.sklep, exp.typ FROM wydatki exp WHERE exp.data LIKE ? and exp.typ LIKE ? ",
                new String[]{"%/" + miesiac + "/%", "%" + typWydatku + "%"});

        listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.raport_szczegoly,
                cursor,
                new String[] { WydatkiKontrakt.Columns.NAZWA_WYDATKU
                        , WydatkiKontrakt.Columns.CENA_WYDATKU},
                new int[] { R.id.expenseName, R.id.expensePrice},
                0
        );
        sumaWydatkow = 0;
        for(int i=0; i< listAdapter.getCount(); i++){
            Cursor cursorCena = (Cursor) listAdapter.getItem(i);
            sumaWydatkow += cursorCena.getInt(cursor.getColumnIndex(WydatkiKontrakt.Columns.CENA_WYDATKU));
        }
        sumaWydatkowText = (TextView) findViewById(R.id.sumaWydatkow);
        sumaWydatkowText.setText(String.valueOf(sumaWydatkow));
        setListAdapter(listAdapter);

    }

}
