package com.example.uts_pbp;

import static com.example.uts_pbp.notification.NotificationActivity.CHANNEL_1_ID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
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
    private BottomNavigationView bottomNavigationView;
    private UserPreferences userPreferences;
    private MaterialButton btnBack;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userPreferences=new UserPreferences(MainActivity.this);
        btnBack=findViewById(R.id.btnBack);


        checkPlay();

        notificationManager = NotificationManagerCompat.from(this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPreferences.logout();
                Toast.makeText(MainActivity.this, "Sampai Jumpa Lagi!!!", Toast.LENGTH_SHORT).show();
                checkPlay();
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.beranda);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.beranda:
                        return true;
                    case R.id.baca:
                        startActivity(new Intent(MainActivity.this, TampilDataAlphabet.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.lokasi:
                        return false;
                    case R.id.tentang:
                        startActivity(new Intent(MainActivity.this, TentangActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    public void sendOnChannel1(View v) {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_baseline_group_24)
                .setContentTitle("Babaii!!!")
                .setContentText("Sampai Jumpa Lagi")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();

        notificationManager.notify(1,notification);
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