package com.example.duknispoldasulsel;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    private NotificationManager mNotifyManager;
    private static final String TAG = "MainActivity";
    public static final String CHANNEL_ID_1 = "Channel 1";
    public static final String CHANNEL_ID_2 = "Channel 2";

    @Override
    public void onCreate() {
        super.onCreate();
        buatNotifikasiChannel();
    }

    private void buatNotifikasiChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel Channel1 =
                    new NotificationChannel
                            (CHANNEL_ID_1, "Channel 1", NotificationManager.IMPORTANCE_HIGH);

            Channel1.setDescription("ini adalah channel 1");
            Channel1.enableVibration(true);
            Channel1.enableLights(true);
            Channel1.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            mNotifyManager = getSystemService(NotificationManager.class);
            mNotifyManager.createNotificationChannel(Channel1);
        }
    }
}
