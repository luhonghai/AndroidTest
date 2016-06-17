package com.luhonghai.android.test.androidtest.data;

/**
 * Created by luhonghai on 6/16/16.
 * A simple mobile request object that will be converted to JSON
 */
public class MobileRequestData extends RequestData {

    private String extra;

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
