package com.luhonghai.android.test.androidtest;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;

import com.luhonghai.android.test.androidtest.data.CallData;
import com.luhonghai.android.test.androidtest.data.LocationData;
import com.luhonghai.android.test.androidtest.event.ConnectivityChangeEvent;
import com.luhonghai.android.test.androidtest.exception.LocationServiceException;
import com.luhonghai.android.test.androidtest.exception.NoNetworkConnectedException;
import com.luhonghai.android.test.androidtest.service.LocationService;
import com.luhonghai.android.test.androidtest.utils.NetworkUtils;

import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luhonghai on 6/16/16.
 * The main activity
 *
 */

public class MainActivity extends BaseActivity {

    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATE = 19;

    private static final long MINIMUM_TIME_BETWEEN_UPDATE = 10000;

    private static final String TAG = "MainActivity";

    @BindView(R.id.txtWifi)
    TextView txtWifi;

    @BindView(R.id.txtMobile)
    TextView txtMobile;

    private LocationManager lm;

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d(TAG, "onLocationChanged");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d(TAG, "onStatusChanged");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d(TAG, "onProviderEnabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d(TAG, "onProviderDisabled");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        updateView();

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        requestLocationUpdate();
    }

    private void requestLocationUpdate() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //TODO Warm user to enable location service
        } else {
            lm.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    MINIMUM_TIME_BETWEEN_UPDATE,
                    MINIMUM_DISTANCE_CHANGE_FOR_UPDATE,
                    locationListener);
            lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
    }

    @OnClick(R.id.btnRequest)
    public void clickButtonRequest() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                LocationService locationService = new LocationService(MainActivity.this);
                try {
                    locationService.requestLocation();
                } catch (IOException | NoNetworkConnectedException | LocationServiceException e) {
                    Log.e(TAG, "Could not request location", e);
                }
                return null;
            }
        }.execute();

    }

    /**
     * This method will be called when connectivity is changed.
     * Subscribe by EventBus
     * @param event Event object
     */
    @Subscribe
    public void onConnectivityChangeEvent(ConnectivityChangeEvent event) {
        Log.d(TAG, "onConnectivityChangeEvent: message " + event.getMessage());
        updateView();
    }

    /**
     * This method will be called when location data is received from API.
     * @param locationData
     */
    @Subscribe
    public void onLocationDataReceived(LocationData locationData) {
        Log.d(TAG, "onLocationDataReceived");
        Intent intent = new Intent(Constant.ACTION_PROXIMITY_ALERT);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //TODO Warm user to enable location service
        } else {
            Log.d(TAG, "onLocationDataReceived addProximityAlert");
            lm.addProximityAlert(locationData.getLatitude(),
                    locationData.getLongitude(), locationData.getRadius(), -1, pendingIntent);
        }
    }

    /**
     * This method will be called when call data is received from API.
     * @param callData
     */
    @Subscribe
    public void onCallDataReceived(CallData callData) {
        Log.d(TAG, "onCallDataReceived");
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
        requestLocationUpdate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //TODO Warm user to enable location service
        } else {
            lm.removeUpdates(locationListener);
        }
    }

    private void updateView() {
        txtWifi.setText(getString(R.string.wifi_state, NetworkUtils.isWifiConnected(this) ? "Connected" : "Not connected"));
        txtMobile.setText(getString(R.string.mobile_state, NetworkUtils.isMobileConnected(this) ? "Connected" : "Not connected"));
    }
}
