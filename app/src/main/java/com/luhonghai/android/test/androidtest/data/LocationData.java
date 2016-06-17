package com.luhonghai.android.test.androidtest.data;

/**
 * Created by luhonghai on 6/16/16.
 */
public class LocationData extends ResponseData {

    private double latitude;

    private double longitude;

    private float radius;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
