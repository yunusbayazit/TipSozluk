package com.example.yunus.tipterimleri;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.yunus.tipterimleri.Adapter.historyAdapter;
import com.example.yunus.tipterimleri.Model.Kelime;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    final List<Kelime> Kelimeler=new ArrayList<Kelime>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Kelimeler.add(new Kelime("Ceviri", "Kelime"));
        Kelimeler.add(new Kelime("Ceviri","Kelime"));
        Kelimeler.add(new Kelime("Ceviri","Kelime"));
        Kelimeler.add(new Kelime("Ceviri","Kelime"));
        Kelimeler.add(new Kelime("Ceviri","Kelime"));
        Kelimeler.add(new Kelime("Ceviri","Kelime"));
        Kelimeler.add(new Kelime("Ceviri","Kelime"));
        Kelimeler.add(new Kelime("Ceviri", "Kelime"));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final ListView listemiz = (ListView) findViewById(R.id.left_drawer_list);
        historyAdapter adaptorumuz=new historyAdapter(this, Kelimeler);
        listemiz.setAdapter(adaptorumuz);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
