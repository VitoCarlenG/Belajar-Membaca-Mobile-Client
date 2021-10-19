package com.example.uts_pbp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.uts_pbp.preferences.UserPreferences;

public class GreenActivity extends AppCompatActivity {
    private ImageView button_green;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.green_page);
        ActionBar actionBar=getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#42DD64"));
        actionBar.setBackgroundDrawable(cd);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        userPreferences=new UserPreferences(GreenActivity.this);
        button_green=findViewById(R.id.button_green);

        checkPlay();

        button_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPreferences.setPlay();
                checkPlay();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_hijau,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.merah){
            startActivity(new Intent(GreenActivity.this, RedActivity.class));
            overridePendingTransition(0, 0);
            finish();
        } else {
            startActivity(new Intent(GreenActivity.this, BlueActivity.class));
            overridePendingTransition(0, 0);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkPlay() {
        if(userPreferences.checkPlay()) {
            startActivity(new Intent(GreenActivity.this, MainActivity.class));
            Toast.makeText(GreenActivity.this, "Selamat Belajar!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}