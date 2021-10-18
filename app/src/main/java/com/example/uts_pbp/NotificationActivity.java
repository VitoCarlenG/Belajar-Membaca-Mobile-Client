package com.example.uts_pbp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.messaging.FirebaseMessaging;

public class NotificationActivity extends Application {
    public static final String CHANNEL_1_ID = "channel1";

    @RequiresApi(api=Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseMessaging.getInstance().subscribeToTopic("sample_notification");

        createNotificationChannel();
    }

    @RequiresApi(api=Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }
}
