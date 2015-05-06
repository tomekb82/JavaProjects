package com.example.rodzinneWydatki;

import android.app.Activity;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tomek on 03.05.15.
 */
public class RaportSzczegoly extends ListActivity {


    private int miesiac;
    private SimpleCursorAdapter listAdapter;
    protected int sumaWydatkow;
    protected TextView sumaWydatkowText;

    protected List<WydatekAkcja> actions;
    protected WydatekAkcjaAdapter adapter;
    private static final int NORMAL_COLOR = 0xFF00FF00;
    private static final int MEDIUM_COLOR = 0xFFFFFC1A;
    private static final int MEDIUM_VALUE = 300;
    private static final int CRITICAL_COLOR =0xFFFF0000;
    private static final int CRITICAL_VALUE = 600;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        miesiac = getIntent().getIntExtra("MIESIAC", 0);
        SQLiteDatabase db = (new WydatkiDBHepler(this)).getWritableDatabase();
        miesiac--;
        actions = new ArrayList<WydatekAkcja>();

        actions.add(new WydatekAkcja(TypWydatku.ROZRYWKA.getValue() + " (" + + getReportsTypeMonthSum(db, TypWydatku.ROZRYWKA.getValue(), miesiac) + " pln)",
                "(" + String.valueOf(getReportsTypeCount(db, TypWydatku.ROZRYWKA.getValue(), miesiac)) + ")",
                WydatekAkcja.AKCJA_RAPORT_ROZRYWKA, getReportsTypeColor(db,TypWydatku.ROZRYWKA.getValue(),miesiac)));

        actions.add(new WydatekAkcja(TypWydatku.DZIECKO.getValue() + " (" + + getReportsTypeMonthSum(db, TypWydatku.DZIECKO.getValue(), miesiac) + " pln)",
                "(" + String.valueOf(getReportsTypeCount(db, TypWydatku.DZIECKO.getValue(), miesiac)) + ")",
                WydatekAkcja.AKCJA_RAPORT_DZIECKO, getReportsTypeColor(db,TypWydatku.DZIECKO.getValue(),miesiac)));

        actions.add(new WydatekAkcja(TypWydatku.DOMOWE.getValue() + " (" + + getReportsTypeMonthSum(db, TypWydatku.DOMOWE.getValue(), miesiac) + " pln)",
                "(" + String.valueOf(getReportsTypeCount(db, TypWydatku.DOMOWE.getValue(), miesiac)) + ")",
                WydatekAkcja.AKCJA_RAPORT_DOMOWE, getReportsTypeColor(db,TypWydatku.DOMOWE.getValue(),miesiac)));

        actions.add(new WydatekAkcja(TypWydatku.RACHUNKI.getValue() + " (" + + getReportsTypeMonthSum(db, TypWydatku.RACHUNKI.getValue(), miesiac) + " pln)",
                "(" + String.valueOf(getReportsTypeCount(db, TypWydatku.RACHUNKI.getValue(), miesiac)) + ")",
                WydatekAkcja.AKCJA_RAPORT_RACHUNKI, getReportsTypeColor(db,TypWydatku.RACHUNKI.getValue(),miesiac)));

        actions.add(new WydatekAkcja(TypWydatku.SPOZYWCZE.getValue() + " (" + + getReportsTypeMonthSum(db, TypWydatku.SPOZYWCZE.getValue(), miesiac) + " pln)",
                "(" + String.valueOf(getReportsTypeCount(db, TypWydatku.SPOZYWCZE.getValue(), miesiac)) + ")",
                WydatekAkcja.AKCJA_RAPORT_SPOZYWCZE, getReportsTypeColor(db,TypWydatku.SPOZYWCZE.getValue(),miesiac)));

        actions.add(new WydatekAkcja(TypWydatku.TOMEK.getValue() + " (" + + getReportsTypeMonthSum(db, TypWydatku.TOMEK.getValue(), miesiac) + " pln)",
                "(" + String.valueOf(getReportsTypeCount(db, TypWydatku.TOMEK.getValue(), miesiac)) + ")",
                WydatekAkcja.AKCJA_RAPORT_TOMEK, getReportsTypeColor(db,TypWydatku.TOMEK.getValue(),miesiac)));

        actions.add(new WydatekAkcja(TypWydatku.KAMILA.getValue() + " (" + + getReportsTypeMonthSum(db, TypWydatku.KAMILA.getValue(), miesiac) + " pln)",
                "(" + String.valueOf(getReportsTypeCount(db, TypWydatku.KAMILA.getValue(), miesiac)) + ")",
                WydatekAkcja.AKCJA_RAPORT_KAMILA, getReportsTypeColor(db,TypWydatku.KAMILA.getValue(),miesiac)));

        actions.add(new WydatekAkcja(TypWydatku.INNE.getValue() + " (" + + getReportsTypeMonthSum(db, TypWydatku.INNE.getValue(), miesiac) + " pln)",
                "(" + String.valueOf(getReportsTypeCount(db, TypWydatku.INNE.getValue(), miesiac)) + ")",
                WydatekAkcja.AKCJA_RAPORT_INNE, getReportsTypeColor(db,TypWydatku.INNE.getValue(),miesiac)));

        adapter = new WydatekAkcjaAdapter();
        setListAdapter(adapter);

        /*
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
        setListAdapter(listAdapter); */

    }

    public void onListItemClick(ListView parent, View view, int position, long id) {
        WydatekAkcja action = actions.get(position);
        switch (action.getType()) {
            case WydatekAkcja.AKCJA_RAPORT_ROZRYWKA:
                idzRaportTyp(TypWydatku.ROZRYWKA.getValue());
                break;
            case WydatekAkcja.AKCJA_RAPORT_DZIECKO:
                idzRaportTyp(TypWydatku.DZIECKO.getValue());
                break;
            case WydatekAkcja.AKCJA_RAPORT_DOMOWE:
                idzRaportTyp(TypWydatku.DOMOWE.getValue());
                break;
            case WydatekAkcja.AKCJA_RAPORT_RACHUNKI:
                idzRaportTyp(TypWydatku.RACHUNKI.getValue());
                break;
            case WydatekAkcja.AKCJA_RAPORT_SPOZYWCZE:
                idzRaportTyp(TypWydatku.SPOZYWCZE.getValue());
                break;
            case WydatekAkcja.AKCJA_RAPORT_TOMEK:
                idzRaportTyp(TypWydatku.TOMEK.getValue());
                break;
            case WydatekAkcja.AKCJA_RAPORT_KAMILA:
                idzRaportTyp(TypWydatku.KAMILA.getValue());
                break;
            case WydatekAkcja.AKCJA_RAPORT_INNE:
                idzRaportTyp(TypWydatku.INNE.getValue());
                break;
        }
    }

    private void idzRaportTyp(String typWydatku){
        //Intent intent = new Intent(this, RaportTypWydatkuSzczegoly.class);
        //intent.putExtra("TYP_WYDATKU", typWydatky);
        //startActivity(intent);
    }

    private int getReportsTypeCount(SQLiteDatabase db, String type, int month) {
        Cursor cursor = db.rawQuery("SELECT count(*) FROM wydatki WHERE data LIKE ? and typ LIKE ? ",
                new String[]{"%/" + month + "/%", "%" + type + "%"});
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    private int getReportsTypeMonthSum(SQLiteDatabase db, String type, int month) {
        Cursor cursor = db.rawQuery("SELECT sum(cena) FROM wydatki WHERE data LIKE ? and typ LIKE ?",
                new String[]{"%/" + month + "/%", "%" + type + "%"});
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    private int getReportsTypeColor(SQLiteDatabase db, String type, int month){
        int sum = getReportsTypeMonthSum(db, type, month);
        if (sum > CRITICAL_VALUE) {
            return CRITICAL_COLOR;
        }else if (sum > MEDIUM_VALUE) {
            return MEDIUM_COLOR;
        }
        return NORMAL_COLOR;
    }

    class WydatekAkcjaAdapter extends ArrayAdapter<WydatekAkcja> {

        WydatekAkcjaAdapter() {
            super(RaportSzczegoly.this, R.layout.akcja_lista, actions);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            WydatekAkcja action = actions.get(position);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.akcja_lista, parent, false);
            TextView label = (TextView) view.findViewById(R.id.label);
            label.setText(action.getLabel());
            label.setTextColor(action.getColor());
            TextView data = (TextView) view.findViewById(R.id.data);
            data.setText(action.getData());
            return view;
        }
    }
}
