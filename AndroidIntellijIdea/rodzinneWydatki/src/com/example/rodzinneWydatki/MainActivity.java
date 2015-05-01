package com.example.rodzinneWydatki;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
public class MainActivity extends Activity/*ListActivity*/ {

    private WydatkiDBHepler helper;
    private SimpleCursorAdapter listAdapter;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_example3);//main_activity);

        //updateUI();
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

   /* private void updateUI() {
        helper = new WydatkiDBHepler(MainActivity.this);
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor cursor = sqlDB.query(WydatkiKontrakt.TABLE,
                new String[]{WydatkiKontrakt.Columns._ID, WydatkiKontrakt.Columns.NAZWA_WYDATKU, WydatkiKontrakt.Columns.CENA_WYDATKU},
                null,null,null,null,null);

        listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.main_activity_szczegoly,
                cursor,
                new String[] { WydatkiKontrakt.Columns.NAZWA_WYDATKU
                        , WydatkiKontrakt.Columns.CENA_WYDATKU},
                new int[] { R.id.nazwaWydatku},
                0
        );
        this.setListAdapter(listAdapter);
    }

    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView nazwaWydatku = (TextView) v.findViewById(R.id.nazwaWydatku);
        String wydatek = nazwaWydatku.getText().toString();

        String sql = String.format("DELETE FROM %s WHERE %s = '%s'",
                WydatkiKontrakt.TABLE,
                WydatkiKontrakt.Columns.NAZWA_WYDATKU,
                wydatek);


        helper = new WydatkiDBHepler(MainActivity.this);
        SQLiteDatabase sqlDB = helper.getWritableDatabase();
        sqlDB.execSQL(sql);
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.akcja_dodaj_wydatek:
                Log.d("MainActivity", "Dodaj wydatek");

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Dodaj wydatek");
                builder.setMessage("Nazwa");
                final EditText inputField = new EditText(this);
                builder.setView(inputField);
                builder.setMessage("Cena");
                final EditText inputField2 = new EditText(this);
                builder.setView(inputField2);
                builder.setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(inputField.getText()!=null) {
                            Log.d("MainActivity", inputField.getText().toString());
                            String wydatek = inputField.getText().toString();
                            Log.d("MainActivity", wydatek);

                            helper = new WydatkiDBHepler(MainActivity.this);
                            SQLiteDatabase db = helper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.clear();
                            values.put(WydatkiKontrakt.Columns.NAZWA_WYDATKU, wydatek);
                            values.put(WydatkiKontrakt.Columns.CENA_WYDATKU, "30");
                            db.insertWithOnConflict(WydatkiKontrakt.TABLE, null, values,
                                    SQLiteDatabase.CONFLICT_IGNORE);
                            updateUI();
                        }
                    }
                });
                builder.setNegativeButton("Anuluj", null);
                builder.create().show();
                return true;

            case R.id.akcja_autor:
                Log.d("MainActivity", "Informacja o autorze");
                return true;

            default:
                return false;
        }
    }*/
}
