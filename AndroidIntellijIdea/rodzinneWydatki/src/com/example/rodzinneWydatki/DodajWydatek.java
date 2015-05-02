package com.example.rodzinneWydatki;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.rodzinneWydatki.db.WydatkiDBHepler;
import com.example.rodzinneWydatki.db.WydatkiKontrakt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tomek on 01.05.15.
 */
public class DodajWydatek extends Activity {

    private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private WydatkiDBHepler helper;
    private EditText nazwaWydatku;
    private EditText cenaWydatku;
    private Spinner typWydatku;
    private EditText nazwaSklepu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodaj_wydatek);
    }

    public void powrot(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void anulujDodajWydatekOnClick(View view) {
        powrot();
    }

    public void potwierdzDodajWydatekOnClick(View view) {
        Log.d("DodajWydatek", "potwierdzDodajWydatekOnClick");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Potwierdzenie");
        builder.setMessage("Czy chcesz dodać wydatek?");
        builder.setNegativeButton("Anuluj", null);
        builder.setPositiveButton("Potwierdz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                nazwaWydatku = (EditText) findViewById(R.id.expenseName);
                cenaWydatku = (EditText) findViewById(R.id.expensePrice);
                typWydatku = (Spinner) findViewById(R.id.expenseType);
                nazwaSklepu = (EditText) findViewById(R.id.expenseShop);
                if (nazwaWydatku.getText() != null && cenaWydatku.getText() != null && typWydatku.getSelectedItem() != null) {
                    helper = new WydatkiDBHepler(DodajWydatek.this);
                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.clear();
                    values.put(WydatkiKontrakt.Columns.NAZWA_WYDATKU, nazwaWydatku.getText().toString());
                    values.put(WydatkiKontrakt.Columns.CENA_WYDATKU, cenaWydatku.getText().toString());
                    values.put(WydatkiKontrakt.Columns.TYP_WYDATKU, typWydatku.getSelectedItem().toString());
                    values.put(WydatkiKontrakt.Columns.DATA_WYDATKU, df.format(new Date()));
                    if (nazwaSklepu != null) {
                        values.put(WydatkiKontrakt.Columns.NAZWA_SKLEPU, nazwaSklepu.getText().toString());
                    }
                    Log.d("DodajWydatek", "nazwaWydatku" + nazwaWydatku.getText().toString() +
                            "cenaWydatku" + cenaWydatku.getText().toString() +
                            "typWydatku" + typWydatku.getSelectedItem().toString());
                    db.insertWithOnConflict(WydatkiKontrakt.TABLE, null, values,
                            SQLiteDatabase.CONFLICT_IGNORE);
                    powrot();
                }
            }
        });
        builder.create().show();

    }
}
