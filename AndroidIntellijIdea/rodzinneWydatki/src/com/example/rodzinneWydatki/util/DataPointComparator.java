package com.example.rodzinneWydatki.util;

import com.jjoe64.graphview.series.DataPoint;

import java.util.Comparator;

/**
 * Created by tomek on 12.05.15.
 */
public class DataPointComparator implements Comparator<DataPoint> {

    boolean desc=false;

    public DataPointComparator(boolean desc) {
        super();
        this.desc = desc;
    }
    @Override
    public int compare(DataPoint arg0, DataPoint arg1) {
        return this.desc ? (int) (arg0.getX() - arg1.getX())
                : (int) (arg1.getX() - arg0.getX());
    }
}
