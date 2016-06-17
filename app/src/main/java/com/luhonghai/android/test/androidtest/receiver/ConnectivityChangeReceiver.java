package com.luhonghai.android.test.androidtest.receiver;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.luhonghai.android.test.androidtest.event.ConnectivityChangeEvent;
import com.luhonghai.android.test.androidtest.service.LocationService;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by luhonghai on 6/16/16.
 * A connectivity change receiver
 */
public class ConnectivityChangeReceiver extends WakefulBroadcastReceiver {
    private static final String TAG = "ConnectivityChange";

    @Override
    public void onReceive(Context context, Intent intent) {
        EventBus.getDefault().post(new ConnectivityChangeEvent("Connectivity changed"));
        Intent service = new Intent(context, LocationService.class);
        startWakefulService(context, service);
    }
}
