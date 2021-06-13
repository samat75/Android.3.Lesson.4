package com.example.android3lesson4.data.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherService {

    private static WeatherService mInstance;
    private static Retrofit mRetrofit;
    private static final String BASE_URL = "http://api.openweathermap.org/";

    public static WeatherService getInstance() {
        if (mInstance == null) {
            mInstance = new WeatherService();
        }
        return mInstance;
    }

    public WeatherService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public WeatherApi getWeatherApi() {
        return mRetrofit.create(WeatherApi.class);
    }
}
