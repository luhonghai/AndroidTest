package com.luhonghai.android.test.androidtest.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.luhonghai.android.test.androidtest.event.ConnectivityChangeEvent;
import com.luhonghai.android.test.androidtest.exception.LocationServiceException;
import com.luhonghai.android.test.androidtest.exception.NoNetworkConnectedException;
import com.luhonghai.android.test.androidtest.service.LocationService;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

/**
 * Created by luhonghai on 6/16/16.
 * A connectivity change receiver
 */
public class ConnectivityChangeReceiver extends BroadcastReceiver {
    private static final String TAG = "ConnectivityChange";

    @Override
    public void onReceive(Context context, Intent intent) {
        EventBus.getDefault().post(new ConnectivityChangeEvent("Connectivity changed"));
        LocationService locationService = new LocationService(context);
        try {
            locationService.requestLocation();
        } catch (IOException | NoNetworkConnectedException | LocationServiceException e) {
            Log.e(TAG, "Could not request location", e);
        }
    }
}
