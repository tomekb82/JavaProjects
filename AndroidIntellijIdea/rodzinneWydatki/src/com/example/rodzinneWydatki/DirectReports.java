package com.example.rodzinneWydatki;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DirectReports extends ListActivity {

	protected Cursor cursor=null;
	protected ListAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {

    	super.onCreate(savedInstanceState);
        setContentView(R.layout.direct_reports);

        SQLiteDatabase db = (new DatabaseHelper(this)).getWritableDatabase();

        String typWydatku = getIntent().getStringExtra("TYP_WYDATKU");

		Cursor cursor = db.rawQuery("SELECT exp._id, exp.nazwa, exp.cena, exp.data, exp.sklep, exp.typ FROM wydatki exp WHERE typ LIKE ?",
				new String[]{"%" + typWydatku + "%"});

        cursor.moveToFirst();

        TextView typWydatkuText = (TextView) findViewById(R.id.typ);
		typWydatkuText.setText(cursor.getString(cursor.getColumnIndex("typ")));

		adapter = new SimpleCursorAdapter(
				this, 
				R.layout.wydatki_lista,
				cursor,
				new String[] {"nazwa", "cena", "data"},
				new int[] {R.id.nazwa, R.id.cena, R.id.data});
		setListAdapter(adapter);
    }
    
    public void onListItemClick(ListView parent, View view, int position, long id) {
    	Intent intent = new Intent(this, WydatekSzczegoly.class);
    	Cursor cursor = (Cursor) adapter.getItem(position);
    	intent.putExtra("WYDATEK_ID", cursor.getInt(cursor.getColumnIndex("_id")));
    	startActivity(intent);
    }
    
}