package com.example.rodzinneWydatki;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.example.rodzinneWydatki.db.WydatkiDBHepler;
import com.example.rodzinneWydatki.db.WydatkiKontrakt;

/**
 * Created by tomek on 30.04.15.
 */
public class MainActivity extends ListMenuActivity {

    private static final int AUTHOR_DIALOG_ID = 999;
    private static final int DEL_EXPENSE_ID = 998;
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
        TextView tytulText = (TextView) findViewById(R.id.title);
        tytulText.setText("Lista wydatków");

        updateUI();
        //addListenerOnButton();
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

    /*
    public void addListenerOnButton() {
        usunWydatek = (Button) findViewById(R.id.deleteExpense);
        /*usunWydatek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //View vParent = (View)v.getParent();
                //TextView nazwaWydatku = (TextView) findViewById(R.id.expenseName);
                //wydatek = nazwaWydatku.getText().toString();
                //showDialog(DEL_EXPENSE_ID);
            }
        });
    }*/

    public void showExpenseOnClick(View view){
        View v = (View) view.getParent();
        TextView nazwaWydatku = (TextView) v.findViewById(R.id.expenseName);
        wydatek = nazwaWydatku.getText().toString();

        Intent intent = new Intent(this, WydatekSzczegoly.class);
        helper = new WydatkiDBHepler(MainActivity.this);
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM wydatki WHERE nazwa LIKE ?",
                new String[]{"%" + wydatek + "%"});
        cursor.moveToFirst();
        intent.putExtra("WYDATEK_ID", cursor.getInt(cursor.getColumnIndex("_id")));
        startActivity(intent);
    }

    public void deleteExpenseOnClick(View view){
        View v = (View) view.getParent();
        TextView nazwaWydatku = (TextView) v.findViewById(R.id.expenseName);
        wydatek = nazwaWydatku.getText().toString();
        showDialog(DEL_EXPENSE_ID);
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
                new int[] { R.id.expenseName, R.id.expensePrice},
                0
        );
        sumaWydatkow = 0;
        for(int i=0; i< listAdapter.getCount(); i++){
            Cursor cursorCena = (Cursor) listAdapter.getItem(i);
            sumaWydatkow += cursorCena.getInt(cursor.getColumnIndex(WydatkiKontrakt.Columns.CENA_WYDATKU));
        }
        sumaWydatkowText = (TextView) findViewById(R.id.sumaWydatkow);
        sumaWydatkowText.setText(String.valueOf(sumaWydatkow) + "pln");

        this.setListAdapter(listAdapter);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case AUTHOR_DIALOG_ID:
                return authorDialog();
            case DEL_EXPENSE_ID:
                return deleteExpenseDialog();
        }
        return null;
    }

    private Dialog deleteExpenseDialog(){
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
        return builder.create();
    }
}
