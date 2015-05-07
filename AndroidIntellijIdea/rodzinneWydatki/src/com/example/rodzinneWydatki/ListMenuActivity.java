package com.example.rodzinneWydatki;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.example.rodzinneWydatki.db.WydatkiDBHepler;
import com.example.rodzinneWydatki.db.WydatkiKontrakt;

/**
 * Created by tomek on 07.05.15.
 */
public class ListMenuActivity extends ListActivity {

    private static final int AUTHOR_DIALOG_ID = 999;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.akcja_glowna:
                Log.d("MainActivity", "Wyszukaj wydatek");
                Intent intentGlowna = new Intent(this, MainActivity.class);
                startActivity(intentGlowna);
                return true;
            case R.id.akcja_szukaj_wydatek:
                Log.d("MainActivity", "Wyszukaj wydatek");
                Intent intentWyszukaj = new Intent(this, SzukajWydatek.class);
                startActivity(intentWyszukaj);
                return true;
            case R.id.akcja_dodaj_wydatek:
                Log.d("MainActivity", "Dodaj wydatek");
                Intent intentDodaj = new Intent(this, DodajWydatek.class);
                startActivity(intentDodaj);
                return true;
            case R.id.akcja_raporty:
                Intent intentRaporty = new Intent(this, Raport.class);
                startActivity(intentRaporty);
                return true;
            case R.id.akcja_autor:
                Log.d("MainActivity", "Informacja o autorze");
                showDialog(AUTHOR_DIALOG_ID);
                return true;
            default:
                return false;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case AUTHOR_DIALOG_ID:
                return authorDialog();
        }
        return null;
    }

    protected Dialog authorDialog(){
        AlertDialog.Builder authorDialog = new AlertDialog.Builder(this);
        authorDialog.setTitle("Autor");
        authorDialog.setMessage("Tomasz Belina\n Wersja apliacji: 0.0.1");
        authorDialog.setNegativeButton("Zamknij", null);
        return authorDialog.create();
    }
}
