package com.luhonghai.android.test.androidtest.api;

import com.luhonghai.android.test.androidtest.data.RequestData;
import com.luhonghai.android.test.androidtest.data.ResponseData;

import java.io.IOException;

import retrofit2.Retrofit;

/**
 * Created by luhonghai on 6/16/16.
 */
public abstract class LocationAPI {

    protected final Retrofit retrofit;

    protected LocationAPI(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public abstract ResponseData requestLocation(RequestData data) throws IOException;
}
