package com.example.rodzinneWydatki;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by tomek on 08.05.15.
 */
public class Test extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
/*
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.setTitle("AAA");

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 5),
                new DataPoint(1, 2),
                new DataPoint(2, 5),
                new DataPoint(3, 6),
                new DataPoint(4, 2)
        });
        graph.addSeries(series2);

        // legend
        series.setTitle("styczwń");
        series.setColor(Color.GREEN);
        series2.setTitle("luty");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        */
    }

    }
