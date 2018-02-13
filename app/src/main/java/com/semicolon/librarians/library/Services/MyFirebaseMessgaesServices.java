package com.semicolon.librarians.library.Services;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.semicolon.librarians.library.Activities.Chat_Activity;
import com.semicolon.librarians.library.R;

/**
 * Created by Delta on 07/02/2018.
 */

public class MyFirebaseMessgaesServices extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        CreateNotificationBuilder(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

    }
    public void CreateNotificationBuilder(String title,String body)
    {

        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        String currClass = am.getRunningTasks(1).get(0).topActivity.getClassName();
        Log.e("currclass",currClass);
        Log.e("currclass","fffffffffffffff");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle(title);
        builder.setContentText(body);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        Intent intent = new Intent(this, Chat_Activity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(100,builder.build());

    }
}