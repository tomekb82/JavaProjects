package com.example.rodzinneWydatki;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.rodzinneWydatki.db.WydatkiDBHepler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tomek on 03.05.15.
 */
public class Raport extends ListMenuActivity {

    private TextView january_number;
    private TextView february_number;
    private TextView march_number;
    private TextView april_number;
    private TextView may_number;
    private TextView june_number;

    protected List<WydatekAkcja> actions;
    protected WydatekAkcjaAdapter adapter;
    private static final int NORMAL_COLOR = 0xFF00FF00;
    private static final int MEDIUM_COLOR = 0xFFFFFC1A;
    private static final int MEDIUM_VALUE = 300;
    private static final int CRITICAL_COLOR =0xFFFF0000;
    private static final int CRITICAL_VALUE = 600;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raport);
        TextView tytulText = (TextView) findViewById(R.id.title);
        tytulText.setText("Raport roczny");

        SQLiteDatabase db = (new WydatkiDBHepler(this)).getWritableDatabase();

        actions = new ArrayList<WydatekAkcja>();

        actions.add(new WydatekAkcja("Styczeń (" + getReportsMonthSum(db,Calendar.JANUARY) + " pln)",
                "(" + String.valueOf(getReportsCount(db, Calendar.JANUARY)) + ")",
                WydatekAkcja.AKCJA_RAPORT_STYCZEN, getReportsMonthColor(db, Calendar.JANUARY)));
        actions.add(new WydatekAkcja("Luty (" + getReportsMonthSum(db,Calendar.FEBRUARY) + " pln)",
                "(" + String.valueOf(getReportsCount(db, Calendar.FEBRUARY)) + ")",
                WydatekAkcja.AKCJA_RAPORT_LUTY, getReportsMonthColor(db, Calendar.FEBRUARY)));
        actions.add(new WydatekAkcja("Marzec (" + getReportsMonthSum(db,Calendar.MARCH) + " pln)",
                "(" + String.valueOf(getReportsCount(db, Calendar.MARCH)) + ")",
                WydatekAkcja.AKCJA_RAPORT_MARZEC, getReportsMonthColor(db, Calendar.MARCH)));
        actions.add(new WydatekAkcja("Kwiecień (" + getReportsMonthSum(db,Calendar.APRIL) + " pln)",
                "(" + String.valueOf(getReportsCount(db, Calendar.APRIL)) + ")",
                WydatekAkcja.AKCJA_RAPORT_KWIECIEN, getReportsMonthColor(db, Calendar.APRIL)));
        actions.add(new WydatekAkcja("Maj (" + getReportsMonthSum(db,Calendar.MAY) + " pln)",
                "(" + String.valueOf(getReportsCount(db, Calendar.MAY)) + ")",
                WydatekAkcja.AKCJA_RAPORT_MAJ, getReportsMonthColor(db, Calendar.MAY)));
        actions.add(new WydatekAkcja("Czerwiec(" + getReportsMonthSum(db,Calendar.JUNE) + " pln)",
                "(" + String.valueOf(getReportsCount(db, Calendar.JUNE)) + ")",
                WydatekAkcja.AKCJA_RAPORT_CZERWIEC, getReportsMonthColor(db, Calendar.JUNE)));

        adapter = new WydatekAkcjaAdapter();
        setListAdapter(adapter);

    }

    public void onListItemClick(ListView parent, View view, int position, long id) {
        WydatekAkcja action = actions.get(position);
        switch (action.getType()) {
            case WydatekAkcja.AKCJA_RAPORT_STYCZEN:
                idzRaportMiesiac(Calendar.JANUARY);
                break;
            case WydatekAkcja.AKCJA_RAPORT_LUTY:
                idzRaportMiesiac(Calendar.FEBRUARY);
                break;
            case WydatekAkcja.AKCJA_RAPORT_MARZEC:
                idzRaportMiesiac(Calendar.MARCH);
                break;
            case WydatekAkcja.AKCJA_RAPORT_KWIECIEN:
                idzRaportMiesiac(Calendar.APRIL);
                break;
            case WydatekAkcja.AKCJA_RAPORT_MAJ:
                idzRaportMiesiac(Calendar.MAY);
                break;
            case WydatekAkcja.AKCJA_RAPORT_CZERWIEC:
                idzRaportMiesiac(Calendar.JUNE);
                break;
        }
    }

    private void idzRaportMiesiac(int miesiac){
        Intent intent = new Intent(this, RaportSzczegoly.class);
        intent.putExtra("MIESIAC", miesiac+1);
        startActivity(intent);
    }

    private int getReportsCount(SQLiteDatabase db, int month) {
        Cursor cursor = db.rawQuery("SELECT count(*) FROM wydatki WHERE data LIKE ?",
                new String[]{"%/" + (month+1) + "/%"});
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    private int getReportsMonthSum(SQLiteDatabase db, int month) {
        Cursor cursor = db.rawQuery("SELECT sum(cena) FROM wydatki WHERE data LIKE ?",
                new String[]{"%/" + (month+1) + "/%"});
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    private int getReportsMonthColor(SQLiteDatabase db, int month){
        int sum = getReportsMonthSum(db, month);
        if (sum > CRITICAL_VALUE) {
            return CRITICAL_COLOR;
        }else if (sum > MEDIUM_VALUE) {
            return MEDIUM_COLOR;
        }
        return NORMAL_COLOR;
    }

    class WydatekAkcjaAdapter extends ArrayAdapter<WydatekAkcja> {

        WydatekAkcjaAdapter() {
            super(Raport.this, R.layout.akcja_lista, actions);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            WydatekAkcja action = actions.get(position);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.akcja_lista, parent, false);
            TextView label = (TextView) view.findViewById(R.id.label);
            label.setText(action.getLabel());
            label.setTextColor(action.getColor());
            TextView data = (TextView) view.findViewById(R.id.data);
            data.setText(action.getData());
            return view;
        }
    }
}
