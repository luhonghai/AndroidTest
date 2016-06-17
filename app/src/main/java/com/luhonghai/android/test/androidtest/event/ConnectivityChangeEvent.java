package com.luhonghai.android.test.androidtest.event;

/**
 * Created by luhonghai on 6/16/16.
 *  Simple connectivity changed event
 *  A quick demo for useful library EventBus https://github.com/greenrobot/EventBus
 */
public class ConnectivityChangeEvent {

    private final String message;

    public ConnectivityChangeEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
