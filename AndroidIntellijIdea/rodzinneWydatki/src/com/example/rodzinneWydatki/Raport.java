package com.example.rodzinneWydatki;

import android.app.ListActivity;
import android.content.Intent;
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
public class Raport extends ListActivity {

    private TextView january_number;
    private TextView february_number;
    private TextView march_number;
    private TextView april_number;
    private TextView may_number;
    private TextView june_number;

    protected List<WydatekAkcja> actions;
    protected WydatekAkcjaAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raport);

        SQLiteDatabase db = (new WydatkiDBHepler(this)).getWritableDatabase();

        actions = new ArrayList<WydatekAkcja>();

        actions.add(new WydatekAkcja("Styczeń", "(" + String.valueOf(getReportsCount(db, Calendar.JANUARY)) + ")",
                WydatekAkcja.AKCJA_RAPORT_STYCZEN, null));
        actions.add(new WydatekAkcja("Luty", "(" + String.valueOf(getReportsCount(db, Calendar.FEBRUARY)) + ")",
                WydatekAkcja.AKCJA_RAPORT_LUTY, null));
        actions.add(new WydatekAkcja("Marzec", "(" + String.valueOf(getReportsCount(db, Calendar.MARCH)) + ")",
                WydatekAkcja.AKCJA_RAPORT_MARZEC, null));
        actions.add(new WydatekAkcja("Kwiecień", "(" + String.valueOf(getReportsCount(db, Calendar.APRIL)) + ")",
                WydatekAkcja.AKCJA_RAPORT_KWIECIEN, null));
        String color = "#FFFFFF";
        if (getReportsCount(db, Calendar.MAY) > 620){
            color ="#FF0000";
        }
        actions.add(new WydatekAkcja("Maj", "(" + String.valueOf(getReportsCount(db, Calendar.MAY)) + ")",
                WydatekAkcja.AKCJA_RAPORT_MAJ, color));
        actions.add(new WydatekAkcja("Czerwiec", "(" + String.valueOf(getReportsCount(db, Calendar.JUNE)) + ")",
                WydatekAkcja.AKCJA_RAPORT_CZERWIEC, null));

        /*
        january_number = (TextView) findViewById(R.id.january_number);
        january_number.setText("(" + String.valueOf(getReportsCount(db, Calendar.JANUARY)) + ")");

        february_number = (TextView) findViewById(R.id.february_number);
        february_number.setText("(" + String.valueOf(getReportsCount(db, Calendar.FEBRUARY)) + ")");

        march_number = (TextView) findViewById(R.id.march_number);
        march_number.setText("(" + String.valueOf(getReportsCount(db, Calendar.MARCH)) + ")");

        april_number = (TextView) findViewById(R.id.april_number);
        april_number.setText("(" + String.valueOf(getReportsCount(db, Calendar.APRIL)) + ")");

        may_number = (TextView) findViewById(R.id.may_number);
        may_number.setText("(" + String.valueOf(getReportsCount(db, Calendar.MAY)) + ")");

        june_number = (TextView) findViewById(R.id.june_number);
        june_number.setText("(" + String.valueOf(getReportsCount(db, Calendar.JUNE)) + ")");
*/
        adapter = new WydatekAkcjaAdapter();
        setListAdapter(adapter);

    }

    public void onListItemClick(ListView parent, View view, int position, long id) {
        WydatekAkcja action = actions.get(position);
        switch (action.getType()) {
            case WydatekAkcja.AKCJA_RAPORT_STYCZEN:
                idzRaportMiesiac(0);
                break;
            case WydatekAkcja.AKCJA_RAPORT_LUTY:
                idzRaportMiesiac(1);
                break;
            case WydatekAkcja.AKCJA_RAPORT_MARZEC:
                idzRaportMiesiac(2);
                break;
            case WydatekAkcja.AKCJA_RAPORT_KWIECIEN:
                idzRaportMiesiac(3);
                break;
            case WydatekAkcja.AKCJA_RAPORT_MAJ:
                idzRaportMiesiac(4);
                break;
            case WydatekAkcja.AKCJA_RAPORT_CZERWIEC:
                idzRaportMiesiac(5);
                break;
        }
    }

    private void idzRaportMiesiac(int miesiac){
        Intent intent = new Intent(this, RaportSzczegoly.class);
        intent.putExtra("MIESIAC", miesiac);
        startActivity(intent);
    }

    private int getReportsCount(SQLiteDatabase db, int month) {
        Cursor cursor = db.rawQuery("SELECT count(*) FROM wydatki WHERE data LIKE ?",
                new String[]{"%/" + month + "/%"});
        cursor.moveToFirst();
        return cursor.getInt(0);
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
            TextView data = (TextView) view.findViewById(R.id.data);
            data.setText(action.getData());

            label.setTextColor(action.getColor());
            return view;
        }

    }
}
