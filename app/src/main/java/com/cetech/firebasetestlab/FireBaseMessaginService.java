package com.cetech.firebasetestlab;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.RawRes;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by oemy9 on 02/04/2017.
 */

public class FireBaseMessaginService extends FirebaseMessagingService {
    public static final String TAG = "FCM SERVICE";

    @Override
    public void onMessageReceived (RemoteMessage remoteMessage) {

        Map <String, String> data = remoteMessage.getData();

        String text = data.get("text");

        sendNotification(this, text, R.raw.sismo_detectado_2);
    }

    public static void sendNotification (Context ctx, String text, @RawRes int sound) {

        AudioManager mobilemode = (AudioManager) ctx.getSystemService(Context.AUDIO_SERVICE);
        mobilemode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);


        // Turn on all sound

        // turn on sound, enable notifications
        mobilemode.setStreamMute(AudioManager.STREAM_SYSTEM, false);
        //notifications
        mobilemode.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);


        int previousNotificationVolume =mobilemode.getStreamVolume(AudioManager.STREAM_NOTIFICATION);

        mobilemode.setStreamVolume(AudioManager.STREAM_NOTIFICATION,mobilemode.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION), 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx);
        builder.setContentText(text);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(ctx.getString(R.string.app_name));
        builder.setContentIntent(PendingIntent.getActivity(ctx, 0,
                new Intent(ctx, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));
        builder.setAutoCancel(true);
        Uri customSoundUri = Uri.parse("android.resource://" + ctx.getPackageName() + "/" + sound);
        builder.setSound(customSoundUri);
        NotificationManagerCompat.from(ctx).notify(0, builder.build());
        mobilemode.setStreamVolume(AudioManager.STREAM_NOTIFICATION,previousNotificationVolume, 0);

    }
}
