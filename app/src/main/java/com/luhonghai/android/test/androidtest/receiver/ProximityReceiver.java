package com.luhonghai.android.test.androidtest.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;

import com.luhonghai.android.test.androidtest.MainActivity;
import com.luhonghai.android.test.androidtest.R;

/**
 * Created by luhonghai on 6/16/16.
 */
public class ProximityReceiver extends BroadcastReceiver {

    private static final String TAG = "ProximityReceiver";

    private static final int NOTIFICATION_ID = 1703;

    @Override
    public void onReceive(Context context, Intent intent) {
        String key = LocationManager.KEY_PROXIMITY_ENTERING;
        Boolean entering = intent.getBooleanExtra(key, false);
        Log.d(TAG, "ProximityReceiver onReceive: entering " + entering);
        if (entering) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
            Notification.Builder builder = new Notification.Builder(context)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Entered");
            Notification notification = builder.build();
            notificationManager.notify(NOTIFICATION_ID, notification);
        }
    }
}
