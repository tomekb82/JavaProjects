package com.example.rodzinneWydatki;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.example.rodzinneWydatki.db.WydatkiDBHepler;
import com.example.rodzinneWydatki.db.WydatkiKontrakt;

/**
 * Created by tomek on 30.04.15.
 */
public class MainActivity extends ListActivity {

    private WydatkiDBHepler helper;
    private SimpleCursorAdapter listAdapter;
    protected int sumaWydatkow;
    protected TextView sumaWydatkowText;
    String wydatek;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        updateUI();
        /*
        SQLiteDatabase sqlDB = new WydatkiDBHepler(this).getWritableDatabase();
        Cursor cursor = sqlDB.query(WydatkiKontrakt.TABLE,
                new String[]{WydatkiKontrakt.Columns.NAZWA_WYDATKU},
                null,null,null,null,null);
        cursor.moveToFirst();
        while(cursor.moveToNext()) {
            Log.d("MainActivity cursor",
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    WydatkiKontrakt.Columns.NAZWA_WYDATKU)));
        }*/
    }

    private void updateUI() {
        helper = new WydatkiDBHepler(MainActivity.this);
        SQLiteDatabase sqlDB = helper.getReadableDatabase();

        //helper.onUpgrade(sqlDB,0,0); // TO CREATE NEW DB

        Cursor cursor = sqlDB.query(WydatkiKontrakt.TABLE,
                new String[]{WydatkiKontrakt.Columns._ID, WydatkiKontrakt.Columns.NAZWA_WYDATKU, WydatkiKontrakt.Columns.CENA_WYDATKU},
                null,null,null,null,null);

        listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.main_activity_szczegoly,
                cursor,
                new String[] { WydatkiKontrakt.Columns.NAZWA_WYDATKU
                        , WydatkiKontrakt.Columns.CENA_WYDATKU},
                new int[] { R.id.nazwaWydatku, R.id.cenaWydatku},
                0
        );
        sumaWydatkow = 0;
        for(int i=0; i< listAdapter.getCount(); i++){
            Cursor cursorCena = (Cursor) listAdapter.getItem(i);
            sumaWydatkow += cursorCena.getInt(cursor.getColumnIndex(WydatkiKontrakt.Columns.CENA_WYDATKU));
        }
        sumaWydatkowText = (TextView) findViewById(R.id.sumaWydatkow);
        sumaWydatkowText.setText(String.valueOf(sumaWydatkow));

        this.setListAdapter(listAdapter);
    }

    public void usunWydatekOnClick(View view) {

        View v = (View) view.getParent();
        TextView nazwaWydatku = (TextView) v.findViewById(R.id.nazwaWydatku);
        wydatek = nazwaWydatku.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Potwierdzenie");
        builder.setMessage("Czy chcesz usunąć wydatek '" + wydatek + "' ?");
        builder.setNegativeButton("Anuluj", null);
        builder.setPositiveButton("Potwierdz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String sql = String.format("DELETE FROM %s WHERE %s = '%s'",
                        WydatkiKontrakt.TABLE,
                        WydatkiKontrakt.Columns.NAZWA_WYDATKU,
                        wydatek);
                helper = new WydatkiDBHepler(MainActivity.this);
                SQLiteDatabase sqlDB = helper.getWritableDatabase();
                sqlDB.execSQL(sql);
                updateUI();
            }
        });
        builder.create().show();
    }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.menu_activity, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.akcja_szukaj_wydatek:
                        Log.d("MainActivity", "Wyszukaj wydatek");
                        Intent intentWyszukaj = new Intent(this, SzukajWydatek.class);
                        startActivity(intentWyszukaj);
                        return true;

                    case R.id.akcja_dodaj_wydatek:
                        Log.d("MainActivity", "Dodaj wydatek");
                        Intent intentDodaj = new Intent(this, DodajWydatek.class);
                        startActivity(intentDodaj);
                        return true;

                    case R.id.akcja_autor:
                        Log.d("MainActivity", "Informacja o autorze");
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Autor");
                        builder.setMessage("Tomasz Belina\n Wersja apliacji: 0.0.1");
                        builder.setNegativeButton("Zamknij", null);
                        builder.create().show();
                        return true;

                    default:
                        return false;
                }
            }
        }
