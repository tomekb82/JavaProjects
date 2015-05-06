package com.example.rodzinneWydatki;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.rodzinneWydatki.db.WydatkiDBHepler;
import com.example.rodzinneWydatki.db.WydatkiKontrakt;

import java.util.ArrayList;
import java.util.List;

public class SzukajWydatek extends ListActivity {

    protected EditText searchText;
    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected ListAdapter adapter;
    protected int sumaWydatkow;
    protected TextView sumaWydatkowText;
    protected CheckBox entertainmentCheckbox;
    protected CheckBox babyCheckbox;
    protected CheckBox homeCheckbox;
    protected CheckBox groceryShoppingCheckbox;
    protected CheckBox tomekCheckbox;
    protected CheckBox kamilaCheckbox;
    protected CheckBox billsCheckbox;
    protected CheckBox otherCheckbox;
    private String types;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.szukaj_wydatek);

        db = (new WydatkiDBHepler(this)).getWritableDatabase();
        searchText = (EditText) findViewById (R.id.searchText);

        entertainmentCheckbox = (CheckBox) findViewById (R.id.entertainment);
        babyCheckbox = (CheckBox) findViewById (R.id.baby);
        homeCheckbox = (CheckBox) findViewById (R.id.home);
        billsCheckbox = (CheckBox) findViewById (R.id.bills);
        groceryShoppingCheckbox = (CheckBox) findViewById (R.id.grocery_shopping);
        tomekCheckbox = (CheckBox) findViewById (R.id.tomek);
        kamilaCheckbox = (CheckBox) findViewById (R.id.kamila);
        otherCheckbox = (CheckBox) findViewById (R.id.other);
    }

    private static String createQuery(int length) {
        String query = "SELECT _id, nazwa, cena, data FROM wydatki WHERE nazwa LIKE ? and typ IN (";
        StringBuilder queryBuilder = new StringBuilder(query);
        for( int i = 0; i< length; i++){
            queryBuilder.append(" ?");
            if(i != length -1) queryBuilder.append(",");
        }
        queryBuilder.append(")");
        return queryBuilder.toString();
    }

    public void search(View view) {
        List<String> types = new ArrayList<String>();
        types.add("%" + searchText.getText().toString() + "%");
        if(entertainmentCheckbox.isChecked()){
            types.add(TypWydatku.ROZRYWKA.getValue());
        }
        if(babyCheckbox.isChecked()){
            types.add(TypWydatku.DZIECKO.getValue());
        }
        if(homeCheckbox.isChecked()){
            types.add(TypWydatku.DOMOWE.getValue());
        }
        if(billsCheckbox.isChecked()){
            types.add(TypWydatku.RACHUNKI.getValue());
        }
        if(groceryShoppingCheckbox.isChecked()){
            types.add(TypWydatku.SPOZYWCZE.getValue());
        }
        if(otherCheckbox.isChecked()){
            types.add(TypWydatku.INNE.getValue());
        }
        if(tomekCheckbox.isChecked()){
            types.add(TypWydatku.TOMEK.getValue());
        }
        if(kamilaCheckbox.isChecked()){
            types.add(TypWydatku.KAMILA.getValue());
        }
        String [] strings = new String[types.size()];
        for(int i=0; i < types.size(); i++){
            strings[i] = types.get(i);
        }

        // || is the concatenation operation in SQLite
        if(types.size()==1) {
            cursor = db.rawQuery("SELECT _id, nazwa, cena, data FROM wydatki WHERE nazwa LIKE ?",
                    new String[]{"%" + searchText.getText().toString() + "%"});
        } else{
            cursor = db.rawQuery(createQuery(types.size()), strings);
        }

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.szukaj_wydatek_lista,
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
