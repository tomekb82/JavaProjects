package com.example.rodzinneWydatki;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Wydatki extends ListActivity {

    protected EditText searchText;
    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected ListAdapter adapter;

    protected int sumaWydatkow;
    protected TextView sumaWydatkowText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        db = (new DatabaseHelper(this)).getWritableDatabase();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        db.execSQL("DROP TABLE IF EXISTS wydatki");
        databaseHelper.onCreate(db);

        searchText = (EditText) findViewById (R.id.searchText);
    }

    public void search(View view) {
        // || is the concatenation operation in SQLite
        cursor = db.rawQuery("SELECT _id, nazwa, cena, data FROM wydatki WHERE nazwa || ' ' || data LIKE ?",
                new String[]{"%" + searchText.getText().toString() + "%"});
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.wydatki_lista,
                cursor,
                new String[] {"nazwa", "cena", "data"},
                new int[] {R.id.nazwa, R.id.cena, R.id.data});

        sumaWydatkow = 0;
        for(int i=0; i< adapter.getCount(); i++){
            Cursor cursorCena = (Cursor) adapter.getItem(i);
            sumaWydatkow += cursorCena.getInt(cursor.getColumnIndex("cena"));
        }
        sumaWydatkowText = (TextView) findViewById(R.id.sumaWydatkow);
        sumaWydatkowText.setText("Suma wydatków:" + String.valueOf(sumaWydatkow));

        setListAdapter(adapter);
    }

    public void onListItemClick(ListView parent, View view, int position, long id) {
        Intent intent = new Intent(this, WydatekSzczegoly.class);
        Cursor cursor = (Cursor) adapter.getItem(position);
        intent.putExtra("WYDATEK_ID", cursor.getInt(cursor.getColumnIndex("_id")));
        startActivity(intent);
    }

}
