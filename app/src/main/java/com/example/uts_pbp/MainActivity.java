package com.example.uts_pbp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.uts_pbp.content.TampilDataAlphabet;
import com.example.uts_pbp.preferences.UserPreferences;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private UserPreferences userPreferences;
    private MaterialButton btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userPreferences=new UserPreferences(MainActivity.this);
        btnBack=findViewById(R.id.btnBack);

        checkPlay();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPreferences.logout();
                Toast.makeText(MainActivity.this, "Sampai Jumpa Lagi!!!", Toast.LENGTH_SHORT).show();
                checkPlay();
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.itm_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.itm_home:
                        return true;
                    case R.id.itm_baca:
                        startActivity(new Intent(MainActivity.this, TampilDataAlphabet.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.itm_lokasi:
                        return false;
                    case R.id.itm_tentang:
                        return false;
                }
                return false;
            }
        });
    }

    private void checkPlay() {
        if (!userPreferences.checkPlay()) {
            startActivity(new Intent(MainActivity.this, BlueActivity.class));
            finish();
        } else {
            Toast.makeText(MainActivity.this, "Selamat Bermain!!!", Toast.LENGTH_SHORT).show();
        }
    }
}