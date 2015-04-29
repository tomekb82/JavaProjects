package com.example.rodzinneWydatki;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class Wydatki extends ListActivity {

    protected EditText searchText;
    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected ListAdapter adapter;
    protected ListView wydatki;


    protected List<Wydatek> przykladoweWydatki = new ArrayList<Wydatek>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
/*
        przykladoweWydatki.add(new Wydatek("szczepionka", new BigDecimal(600.00), new Date()));
        przykladoweWydatki.add(new Wydatek("zakupy", new BigDecimal(260.00), new Date()));

        ListAdapter adapter = new ArrayAdapter<Wydatek>(this, android.R.layout.simple_list_item_1, przykladoweWydatki);
        ListView wydatki = (ListView) findViewById(R.id.list);
        wydatki.setAdapter(adapter);
*/

        db = (new DatabaseHelper(this)).getWritableDatabase();
        DatabaseHelper d = new DatabaseHelper(this);
        d.onCreate(db);
        searchText = (EditText) findViewById (R.id.searchText);
       // wydatki = (ListView) findViewById (an);
    }

    public void search(View view) {
        // || is the concatenation operation in SQLite
        cursor = db.rawQuery("SELECT _id, nazwa, cena, data FROM wydatki WHERE nazwa || ' ' || data LIKE ?",
                new String[]{"%" + searchText.getText().toString() + "%"});
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.wydatki_list_item,
                cursor,
                new String[] {"nazwa", "cena", "data"},
                new int[] {R.id.nazwa, R.id.cena, R.id.data});
        //wydatki.setAdapter(adapter);
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView parent, View view, int position, long id) {
        Intent intent = new Intent(this, WydatekSzczegoly.class);
        Cursor cursor = (Cursor) adapter.getItem(position);
        intent.putExtra("WYDATEK_ID", cursor.getInt(cursor.getColumnIndex("_id")));
        startActivity(intent);
    }

}
