package com.luhonghai.android.test.androidtest.data;

/**
 * Created by luhonghai on 6/16/16.
 *
 * A simple wifi request object that will be converted to JSON
 */
public class WifiRequestData extends RequestData {

    private String wifiInformation;

    public String getWifiInformation() {
        return wifiInformation;
    }

    public void setWifiInformation(String wifiInformation) {
        this.wifiInformation = wifiInformation;
    }
}
