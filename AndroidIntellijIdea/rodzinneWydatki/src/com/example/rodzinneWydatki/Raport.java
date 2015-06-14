package com.example.rodzinneWydatki;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rodzinneWydatki.db.WydatkiDBHepler;
import com.example.rodzinneWydatki.util.DataPointComparator;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

        getReportsMonthValues(db, Calendar.MAY);
        generateActions(db);
        generateChart(db);
        adapter = new WydatekAkcjaAdapter();
        setListAdapter(adapter);
    }

    private void generateActions(SQLiteDatabase db) {
        actions = new ArrayList<WydatekAkcja>();

        actions.add(new WydatekAkcja("Styczeń (" + getReportsMonthSum(db, Calendar.JANUARY) + " pln)",
                "(" + String.valueOf(getReportsCount(db, Calendar.JANUARY)) + ")",
                WydatekAkcja.AKCJA_RAPORT_STYCZEN, getReportsMonthColor(db, Calendar.JANUARY)));
        actions.add(new WydatekAkcja("Luty (" + getReportsMonthSum(db, Calendar.FEBRUARY) + " pln)",
                "(" + String.valueOf(getReportsCount(db, Calendar.FEBRUARY)) + ")",
                WydatekAkcja.AKCJA_RAPORT_LUTY, getReportsMonthColor(db, Calendar.FEBRUARY)));
        actions.add(new WydatekAkcja("Marzec (" + getReportsMonthSum(db, Calendar.MARCH) + " pln)",
                "(" + String.valueOf(getReportsCount(db, Calendar.MARCH)) + ")",
                WydatekAkcja.AKCJA_RAPORT_MARZEC, getReportsMonthColor(db, Calendar.MARCH)));
        actions.add(new WydatekAkcja("Kwiecień (" + getReportsMonthSum(db, Calendar.APRIL) + " pln)",
                "(" + String.valueOf(getReportsCount(db, Calendar.APRIL)) + ")",
                WydatekAkcja.AKCJA_RAPORT_KWIECIEN, getReportsMonthColor(db, Calendar.APRIL)));
        actions.add(new WydatekAkcja("Maj (" + getReportsMonthSum(db, Calendar.MAY) + " pln)",
                "(" + String.valueOf(getReportsCount(db, Calendar.MAY)) + ")",
                WydatekAkcja.AKCJA_RAPORT_MAJ, getReportsMonthColor(db, Calendar.MAY)));
        actions.add(new WydatekAkcja("Czerwiec(" + getReportsMonthSum(db, Calendar.JUNE) + " pln)",
                "(" + String.valueOf(getReportsCount(db, Calendar.JUNE)) + ")",
                WydatekAkcja.AKCJA_RAPORT_CZERWIEC, getReportsMonthColor(db, Calendar.JUNE)));
    }

    private GraphView graph2;
    private Double max = 0.0;
    private Double min = 31.0;
    private PointsGraphSeries<DataPoint>[] seriesArray = new PointsGraphSeries[12];

    private void generateChart(SQLiteDatabase db) {
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.setTitle("Wykres rocznych wydatków");

        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(1, getReportsMonthSum(db, Calendar.JANUARY)),
                new DataPoint(2, getReportsMonthSum(db, Calendar.FEBRUARY)),
                new DataPoint(3, getReportsMonthSum(db, Calendar.MARCH)),
                new DataPoint(4, getReportsMonthSum(db, Calendar.APRIL)),
                new DataPoint(5, getReportsMonthSum(db, Calendar.MAY)),
                new DataPoint(6, getReportsMonthSum(db, Calendar.JUNE)),
                new DataPoint(7, getReportsMonthSum(db, Calendar.JULY)),
                new DataPoint(8, getReportsMonthSum(db, Calendar.AUGUST)),
                new DataPoint(9, getReportsMonthSum(db, Calendar.SEPTEMBER)),
                new DataPoint(10, getReportsMonthSum(db, Calendar.OCTOBER)),
                new DataPoint(11, getReportsMonthSum(db, Calendar.NOVEMBER)),
                new DataPoint(12, getReportsMonthSum(db, Calendar.DECEMBER))

        });
        graph.addSeries(series);

        // styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
            }
        });
        series.setSpacing(50);
        // draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
        //series.setValuesOnTopSize(50);
        // legend
        //series.setTitle("foo");
        //graph.getLegendRenderer().setVisible(true);
        //graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(12);
        graph.getViewport().setScalable(true);
        graph.getGridLabelRenderer().setNumHorizontalLabels(12);

        graph2 = (GraphView) findViewById(R.id.graph2);
        addGraphSeries(db, Calendar.APRIL, "kwiecień", Color.GREEN, PointsGraphSeries.Shape.POINT);
        addGraphSeries(db, Calendar.MAY, "maj", Color.BLUE, PointsGraphSeries.Shape.TRIANGLE);
        addGraphSeries(db, Calendar.JUNE, "czerwiec", Color.YELLOW, PointsGraphSeries.Shape.RECTANGLE);
        graph2.getViewport().setMaxX(max);
        graph2.getViewport().setMinX(min);
        graph2.getLegendRenderer().setVisible(true);
        graph2.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph2.getGridLabelRenderer().setNumHorizontalLabels(max.intValue() - min.intValue() + 1);

        for(int i=0; i < 12; i++) {
            if(seriesArray[i] != null) {
                seriesArray[i].setOnDataPointTapListener(new OnDataPointTapListener() {
                    @Override
                    public void onTap(Series series, DataPointInterface dataPointInterface) {
                        Log.d("setOnDataPointTapListener debug: value", String.valueOf(dataPointInterface.getY()));
                        Toast.makeText("Series1: On Data Point clicked: " + dataPointInterface, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }



    private void addGraphSeries(SQLiteDatabase db, int month, String title, int color, PointsGraphSeries.Shape shape) {
        int i = 0;
        List<String> monthValues = getReportsMonthDayValues(db, month);
        List<DataPoint> dp = new ArrayList<DataPoint>();
        int monthValuesSize = monthValues.size();
        if (monthValuesSize > 0) {
            for (String s : monthValues) {
                String[] ss = s.split(";");
                if (ss.length > 1) {
                    dp.add(new DataPoint(Double.valueOf(ss[0]), Double.valueOf(ss[1])));
                }
            }
            Collections.sort(dp, new DataPointComparator(false));
            DataPoint[] dpArray = new DataPoint[dp.size()];
            for(DataPoint d: dp){
                dpArray[i] = d;
                i+=1;
            }
            PointsGraphSeries<DataPoint> series = new PointsGraphSeries<DataPoint>(dpArray);
            series.setTitle(title);
            series.setColor(color);
            series.setShape(shape);
            series.setSize(10);
            /*series.setOnDataPointTapListener(new OnDataPointTapListener() {
                @Override
                public void onTap(Series series, DataPointInterface dataPointInterface) {
                    Log.d("setOnDataPointTapListener debug: value", String.valueOf(dataPointInterface.getY()));
                }
            });*/
            seriesArray[month] = series;
            graph2.addSeries(seriesArray[month]);
            graph2.getViewport().setXAxisBoundsManual(true);
            double lmin = dp.get(dp.size() - 1).getX();
            if(min > lmin){
                min = lmin;
            }
            double lmax = dp.get(0).getX();
            if(max < lmax) {
                max = lmax;
            }
        }
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
                new String[]{"%/" + (month + 1) + "/%"});
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    private List<String> getReportsMonthValues(SQLiteDatabase db, int month) {
        List<String> wydatekList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT data, cena FROM wydatki WHERE data LIKE ?",
                new String[]{"%/" + (month+1) + "/%"});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String[] ss = cursor.getString(cursor.getColumnIndex("data")).split("/");
            wydatekList.add(ss[1] + ";" + cursor.getString(cursor.getColumnIndex("cena")));
            cursor.moveToNext();
        }
        return wydatekList;
    }

    private List<String> getReportsMonthDayValues(SQLiteDatabase db, int month) {
        List<String> wydatekList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT data, cena FROM wydatki WHERE data LIKE ?",
                new String[]{"%/" + (month+1) + "/%"});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String[] ss = cursor.getString(cursor.getColumnIndex("data")).split("/");
            wydatekList.add(ss[0] + ";" + cursor.getString(cursor.getColumnIndex("cena")));
            cursor.moveToNext();
        }
        return wydatekList;
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
