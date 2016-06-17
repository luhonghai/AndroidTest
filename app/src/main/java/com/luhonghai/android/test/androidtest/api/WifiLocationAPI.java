package com.luhonghai.android.test.androidtest.api;

import com.luhonghai.android.test.androidtest.data.RequestData;
import com.luhonghai.android.test.androidtest.data.ResponseData;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by luhonghai on 6/16/16.
 */
public class WifiLocationAPI extends LocationAPI {

    protected WifiLocationAPI(Retrofit retrofit) {
        super(retrofit);
    }

    @Override
    public ResponseData requestLocation(RequestData data) throws IOException {
        Response<ResponseData> response = retrofit.create(WifiLocationService.class).requestLocation(data).execute();
        return response.body();
    }

    public interface WifiLocationService {
        @POST("api/location/wifi")
        Call<ResponseData> requestLocation(@Body RequestData data);
    }
}
