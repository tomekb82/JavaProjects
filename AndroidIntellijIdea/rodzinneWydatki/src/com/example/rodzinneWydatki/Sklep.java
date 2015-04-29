package com.example.rodzinneWydatki;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by tomek on 29.04.15.
 */
public class Sklep extends Activity {

    protected TextView sklepText;
    protected String sklep;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sklep);

        sklep = getIntent().getStringExtra("NAZWA_SKLEPU");

        sklepText = (TextView) findViewById(R.id.sklep);
        sklepText.setText("Sklepik:" + sklep);
    }
}
