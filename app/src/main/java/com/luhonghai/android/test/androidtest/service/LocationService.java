package com.luhonghai.android.test.androidtest.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.luhonghai.android.test.androidtest.api.LocationAPI;
import com.luhonghai.android.test.androidtest.api.LocationAPIFactory;
import com.luhonghai.android.test.androidtest.data.CallData;
import com.luhonghai.android.test.androidtest.data.LocationData;
import com.luhonghai.android.test.androidtest.data.MobileRequestData;
import com.luhonghai.android.test.androidtest.data.RequestData;
import com.luhonghai.android.test.androidtest.data.ResponseData;
import com.luhonghai.android.test.androidtest.data.WifiRequestData;
import com.luhonghai.android.test.androidtest.data.sqlite.dao.CallDataDAO;
import com.luhonghai.android.test.androidtest.exception.LocationServiceException;
import com.luhonghai.android.test.androidtest.exception.NoNetworkConnectedException;
import com.luhonghai.android.test.androidtest.receiver.ConnectivityChangeReceiver;
import com.luhonghai.android.test.androidtest.utils.NetworkUtils;
import com.luhonghai.litedb.exception.AnnotationNotFound;
import com.luhonghai.litedb.exception.InvalidAnnotationData;
import com.luhonghai.litedb.exception.LiteDatabaseException;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

/**
 * Created by luhonghai on 6/16/16.
 */
public class LocationService extends IntentService {

    private static final String TAG = "LocationService";

    public LocationService() {
        super(LocationService.class.getName());
    }

    public void requestLocation() throws IOException, NoNetworkConnectedException, LocationServiceException {
        LocationAPI locationAPI = LocationAPIFactory.createLocationAPI(this);
        RequestData requestData;
        if (NetworkUtils.isWifiConnected(this)) {
            requestData = new WifiRequestData();
        } else if (NetworkUtils.isMobileConnected(this)) {
            requestData = new MobileRequestData();
        } else {
            throw new NoNetworkConnectedException();
        }
        try {
            ResponseData responseData = locationAPI.requestLocation(requestData);
            if (responseData instanceof CallData) {
                // Save to SQLite database
                Log.d(TAG, "requestLocation: response is call data");
                CallData callData = (CallData) responseData;
                CallDataDAO dataDAO = null;
                try {
                    dataDAO = new CallDataDAO(this);
                    dataDAO.open();
                    dataDAO.insert(callData);
                } catch (AnnotationNotFound | InvalidAnnotationData | LiteDatabaseException e) {
                    throw new LocationServiceException("Could not save call data to database", e);
                } finally {
                    if (dataDAO != null) {
                        dataDAO.close();
                    }
                }
                EventBus.getDefault().post(callData);
            } else if (responseData instanceof LocationData) {
                Log.d(TAG, "requestLocation: response is location data");
                LocationData locationData = (LocationData) responseData;
                EventBus.getDefault().post(locationData);
            } else {
                throw new LocationServiceException("Location API invalid response");
            }
        } catch (Exception e) {
            throw new LocationServiceException("Location API could not request location", e);
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            requestLocation();
        } catch (IOException | NoNetworkConnectedException | LocationServiceException e) {
            Log.e(TAG, "Could not request location", e);
        } finally {
            ConnectivityChangeReceiver.completeWakefulIntent(intent);
        }
    }
}
