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
import android.view.Window;
import android.widget.ImageView;

import com.example.uts_pbp.preferences.UserPreferences;

public class BlueActivity extends AppCompatActivity {
    private ImageView button_blue;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blue_page);
        ActionBar actionBar=getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#2A79EF"));
        actionBar.setBackgroundDrawable(cd);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        userPreferences=new UserPreferences(BlueActivity.this);
        button_blue=findViewById(R.id.button_blue);

        checkPlay();

        button_blue.setOnClickListener(new View.OnClickListener() {
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
        menuInflater.inflate(R.menu.menu_biru,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.merah){
            startActivity(new Intent(BlueActivity.this, RedActivity.class));
            overridePendingTransition(0, 0);
            finish();
        } else {
            startActivity(new Intent(BlueActivity.this, GreenActivity.class));
            overridePendingTransition(0, 0);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkPlay() {
        if(userPreferences.checkPlay()) {
            startActivity(new Intent(BlueActivity.this, MainActivity.class));
            finish();
        }
    }
}