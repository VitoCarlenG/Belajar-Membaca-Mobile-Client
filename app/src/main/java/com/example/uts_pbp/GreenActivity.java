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

public class GreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.green_page);
        ActionBar actionBar=getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#42DD64"));
        actionBar.setBackgroundDrawable(cd);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_hijau,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.biru){
            startActivity(new Intent(GreenActivity.this, BlueActivity.class));
            overridePendingTransition(0, 0);
            finish();
        } else {
            startActivity(new Intent(GreenActivity.this, RedActivity.class));
            overridePendingTransition(0, 0);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}