package com.example.rodzinneWydatki;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.rodzinneWydatki.db.WydatkiDBHepler;
import com.example.rodzinneWydatki.db.WydatkiKontrakt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tomek on 01.05.15.
 */
public class DodajWydatek extends Activity {

    private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private static final int DATE_DIALOG_ID = 999;
    private static final int ADD_EXPENSE_DIALOG_ID = 998;
    private WydatkiDBHepler helper;
    private EditText nazwaWydatku;
    private EditText cenaWydatku;
    private Spinner typWydatku;
    private EditText nazwaSklepu;

    private TextView dataWydatku;
    private Button zmienDate;
    private Button anulujDodajWydatek;
    private Button potwierdzDodajWydatek;
    private int year;
    private int month;
    private int day;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodaj_wydatek);

        setCurrentDateOnView();
        addListenerOnButton();
    }

    // display current date
    public void setCurrentDateOnView() {
        dataWydatku = (TextView) findViewById(R.id.expenseDate);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        dataWydatku.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(day).append("/").append(month).append("/")
                .append(year).append(" "));
    }

    public void addListenerOnButton() {
        zmienDate = (Button) findViewById(R.id.changeDate);
        zmienDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        anulujDodajWydatek = (Button) findViewById(R.id.anulujDodajWydatek);
        anulujDodajWydatek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                powrot();
            }
        });

        potwierdzDodajWydatek = (Button) findViewById(R.id.potwierdzDodajWydatek);
        potwierdzDodajWydatek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(ADD_EXPENSE_DIALOG_ID);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
            case ADD_EXPENSE_DIALOG_ID:
                return addExpenseDialog();
        }

        return null;
    }

    private Dialog addExpenseDialog(){
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
                    if(typWydatku.getSelectedItem().toString().equals("--brak--")){
                        values.put(WydatkiKontrakt.Columns.TYP_WYDATKU, TypWydatku.INNE.getValue());
                    }else{
                        values.put(WydatkiKontrakt.Columns.TYP_WYDATKU, typWydatku.getSelectedItem().toString());
                    }
                    values.put(WydatkiKontrakt.Columns.DATA_WYDATKU, dataWydatku.getText().toString());
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
        return builder.create();
    }
    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            dataWydatku.setText(new StringBuilder().append(day)
                    .append("/").append(month).append("/").append(year)
                    .append(" "));
        }
    };

    public void powrot(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
