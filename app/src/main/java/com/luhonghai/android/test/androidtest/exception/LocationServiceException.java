package com.luhonghai.android.test.androidtest.exception;

/**
 * Created by luhonghai on 6/17/16.
 */
public class LocationServiceException extends Exception {

    public LocationServiceException(String message) {
        super(message);
    }

    public LocationServiceException(String message, Throwable e) {
        super(message, e);
    }
}
