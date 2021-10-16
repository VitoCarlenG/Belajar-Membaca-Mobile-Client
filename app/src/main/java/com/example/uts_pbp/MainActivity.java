package com.example.uts_pbp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.uts_pbp.preferences.UserPreferences;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

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