package com.example.uts_pbp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.uts_pbp.content.TampilDataAlphabet;
import com.example.uts_pbp.geolocation.GeoActivity;
import com.example.uts_pbp.preferences.UserPreferences;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class TentangActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tentang);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.tentang);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logokecil);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.beranda:
                        startActivity(new Intent(TentangActivity.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.baca:
                        startActivity(new Intent(TentangActivity.this, TampilDataAlphabet.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.lokasi:
                        startActivity(new Intent(TentangActivity.this, GeoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.tentang:
                        startActivity(new Intent(TentangActivity.this, TentangActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.latihan:
                        startActivity(new Intent(TentangActivity.this, LatihanActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

}


