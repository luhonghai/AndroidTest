package com.luhonghai.android.test.androidtest.api;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.luhonghai.android.test.androidtest.R;
import com.luhonghai.android.test.androidtest.data.CallData;
import com.luhonghai.android.test.androidtest.data.LocationData;
import com.luhonghai.android.test.androidtest.data.ResponseData;
import com.luhonghai.android.test.androidtest.exception.NoNetworkConnectedException;
import com.luhonghai.android.test.androidtest.utils.NetworkUtils;

import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by luhonghai on 6/16/16.
 */
public class LocationAPIFactory {

    public static class ResponseDeserializer implements JsonDeserializer<ResponseData> {

        @Override
        public ResponseData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Map.Entry<String, JsonElement> entry = json.getAsJsonObject().entrySet().iterator().next();
            switch(entry.getKey()) {
                case "latitude":
                    return context.deserialize(json, LocationData.class);
                case "data":
                    return context.deserialize(json, CallData.class);
                default:
                    throw new IllegalArgumentException("Can't deserialize " + entry.getKey());
            }
        }
    }

    public static LocationAPI createLocationAPI(Context context) throws NoNetworkConnectedException {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(context.getString(R.string.base_url))
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(
                            new GsonBuilder()
                                    .registerTypeAdapter(ResponseData.class, new ResponseDeserializer())
                                    .create()
                    ))
                    .build();
        if (NetworkUtils.isWifiConnected(context)) {
            return new WifiLocationAPI(retrofit);
        } else if (NetworkUtils.isMobileConnected(context)) {
            return new MobileLocationAPI(retrofit);
        } else {
            throw new NoNetworkConnectedException();
        }
    }
}
