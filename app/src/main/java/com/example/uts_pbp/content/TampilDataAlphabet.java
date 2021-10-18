package com.example.uts_pbp.content;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.uts_pbp.MainActivity;
import com.example.uts_pbp.R;
import com.example.uts_pbp.TentangActivity;
import com.example.uts_pbp.geolocation.GeoActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class TampilDataAlphabet extends AppCompatActivity {
    private ArrayList<DataAlphabet> DataAlphabetList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataAlphabetList = new DaftarAlphabet().DataAlphabet;

        //recyclerView
        setContentView(R.layout.activity_tampil_data_alphabet);
        recyclerView = findViewById(R.id.at_alpha);
        adapter = new RecyclerViewAdapter(getApplicationContext(), DataAlphabetList);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.baca);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.beranda:
                        startActivity(new Intent(TampilDataAlphabet.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.baca:
                        startActivity(new Intent(TampilDataAlphabet.this, TampilDataAlphabet.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.lokasi:
                        startActivity(new Intent(TampilDataAlphabet.this, GeoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.tentang:
                        startActivity(new Intent(TampilDataAlphabet.this, TentangActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}