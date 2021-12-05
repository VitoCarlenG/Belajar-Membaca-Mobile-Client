package com.example.uas_pbp;

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

import com.example.uas_pbp.preferences.UserPreferences;

public class RedActivity extends AppCompatActivity {
    private ImageView button_red;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.red_page);
        ActionBar actionBar=getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#ED1C1C"));
        actionBar.setBackgroundDrawable(cd);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        userPreferences=new UserPreferences(RedActivity.this);
        button_red=findViewById(R.id.button_red);

        checkPlay();

        button_red.setOnClickListener(new View.OnClickListener() {
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
        menuInflater.inflate(R.menu.menu_merah,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.hijau){
            startActivity(new Intent(RedActivity.this, GreenActivity.class));
            overridePendingTransition(0, 0);
            finish();
        } else {
            startActivity(new Intent(RedActivity.this, BlueActivity.class));
            overridePendingTransition(0, 0);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkPlay() {
        if(userPreferences.checkPlay()) {
            startActivity(new Intent(RedActivity.this, MainActivity.class));
            Toast.makeText(RedActivity.this, "Selamat Belajar!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}