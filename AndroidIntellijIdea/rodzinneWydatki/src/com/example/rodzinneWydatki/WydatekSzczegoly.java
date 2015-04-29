package com.example.rodzinneWydatki;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomek on 28.04.15.
 */
public class WydatekSzczegoly extends ListActivity {

    protected TextView nazwaWydatku;
    protected int idWydatku;

    protected String sklep;
    protected String typWydatku;

    protected List<WydatekAkcja> actions;
    protected WydatekAkcjaAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wydatki_szczegoly);

        idWydatku = getIntent().getIntExtra("WYDATEK_ID", 0);
        SQLiteDatabase db = (new DatabaseHelper(this)).getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT exp._id, exp.nazwa, exp.cena, exp.data, exp.sklep, exp.typ FROM wydatki exp WHERE exp._id = ?",
                new String[]{"" + idWydatku});

        if (cursor.getCount() == 1) {
            cursor.moveToFirst();

            nazwaWydatku = (TextView) findViewById(R.id.nazwa);
            nazwaWydatku.setText(cursor.getString(cursor.getColumnIndex("nazwa")));

            actions = new ArrayList<WydatekAkcja>();

            sklep = cursor.getString(cursor.getColumnIndex("sklep"));
            if (sklep != null) {
                actions.add(new WydatekAkcja("Nazwa sklepu", sklep, WydatekAkcja.AKCJA_SKLEP));
            }

            typWydatku = cursor.getString(cursor.getColumnIndex("typ"));
            if (typWydatku != null) {
                actions.add(new WydatekAkcja("Typ wydatku", typWydatku, WydatekAkcja.AKCJA_TYP));
            }

            cursor = db.rawQuery("SELECT count(*) FROM wydatki WHERE typ = ?",
                    new String[]{""+typWydatku});
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            if (count>0) {
                actions.add(new WydatekAkcja("Pokaz raporty", "(" + count + ")", WydatekAkcja.AKCJA_RAPORTY));
            }

            adapter = new WydatekAkcjaAdapter();
            setListAdapter(adapter);

        }
    }

    public void onListItemClick(ListView parent, View view, int position, long id) {

        WydatekAkcja action = actions.get(position);

        Intent intent;
        switch (action.getType()) {
/*
            case WydatekAkcja.AKCJA_SKLEP:
                Uri sklepUri = Uri.parse("sklep:" + action.getData());
                intent = new Intent(Intent.ACTION_VIEW, sklepUri);
                startActivity(intent);
                break;
                */
            case WydatekAkcja.AKCJA_SKLEP:
                intent = new Intent(this, Sklep.class);
                intent.putExtra("NAZWA_SKLEPU", sklep);
                startActivity(intent);
                break;

            case WydatekAkcja.AKCJA_TYP:
                Uri typUri = Uri.parse("typ:" + action.getData());
                intent = new Intent(Intent.ACTION_VIEW, typUri);
                startActivity(intent);
                break;

            case WydatekAkcja.AKCJA_RAPORTY:
                intent = new Intent(this, DirectReports.class);
                intent.putExtra("TYP_WYDATKU", typWydatku);
                startActivity(intent);
                break;

            /*
            case EmployeeAction.ACTION_CALL:
                Uri callUri = Uri.parse("tel:" + action.getData());
                intent = new Intent(Intent.ACTION_CALL, callUri);
                startActivity(intent);
                break;
            case EmployeeAction.ACTION_EMAIL:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{action.getData()});
                startActivity(intent);
                break;

            case EmployeeAction.ACTION_SMS:
                Uri smsUri = Uri.parse("sms:" + action.getData());
                intent = new Intent(Intent.ACTION_VIEW, smsUri);
                startActivity(intent);
                break;

            case EmployeeAction.ACTION_VIEW:
                intent = new Intent(this, EmployeeDetails.class);
                intent.putExtra("EMPLOYEE_ID", managerId);
                startActivity(intent);
                break;
            */
        }
    }

    class WydatekAkcjaAdapter extends ArrayAdapter<WydatekAkcja> {

        WydatekAkcjaAdapter() {
            super(WydatekSzczegoly.this, R.layout.akcja_lista, actions);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            WydatekAkcja action = actions.get(position);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.akcja_lista, parent, false);
            TextView label = (TextView) view.findViewById(R.id.label);
            label.setText(action.getLabel());
            TextView data = (TextView) view.findViewById(R.id.data);
            data.setText(action.getData());
            return view;
        }

    }
}
