package com.example.android.androidapp.generator;

import com.example.android.androidapp.BuildConfig;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by anjana on 6/1/16.
 */
public class MovieAPIGenerator {

    private static final int QUERY_TIMEOUT_SECONDS = 30;

    public static final String IMAGE_URL = "http://image.tmdb.org/t/p/w342/";
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String API_KEY = BuildConfig.API_KEY;
    public static final String YOU_TUBE = "http://img.youtube.com/vi/";

    public static <S> S createService(Class<S> serviceClass) {

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
                request.addQueryParam("api_key", API_KEY);
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(QUERY_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(QUERY_TIMEOUT_SECONDS, TimeUnit.SECONDS);

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setClient(new OkClient(okHttpClient))
                .setLogLevel(RestAdapter.LogLevel.FULL);
        builder.setRequestInterceptor(requestInterceptor);
        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);

    }


}
