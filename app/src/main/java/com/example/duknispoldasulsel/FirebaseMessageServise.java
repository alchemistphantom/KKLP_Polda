package com.example.duknispoldasulsel;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FirebaseMessageServise extends FirebaseMessagingService {
    private NotificationManagerCompat managerCompat;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        managerCompat = NotificationManagerCompat.from(this);
//        Map<String, String> data = remoteMessage.getData();
//        String title = data.get("title").toString();
//        String message = data.get("body").toString();
       // Toast.makeText(this, title+","+message, Toast.LENGTH_SHORT).show();
       tampilNotif(remoteMessage.getData().get("title"),remoteMessage.getData().get("message"),remoteMessage.getData().get("id"));
    }
    private void tampilNotif(String title, String message, String id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            managerCompat = NotificationManagerCompat.from(this);
            Intent i = new Intent(this,MainActivity.class);
            i.putExtra("msg",message);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_ID_1)
                    .setSmallIcon(R.drawable.mascot_1)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .build();
            managerCompat.notify(1,notification);
        }


        }
}
