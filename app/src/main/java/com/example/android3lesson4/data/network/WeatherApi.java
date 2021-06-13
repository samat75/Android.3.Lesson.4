package com.example.android3lesson4.data.network;

import com.example.android3lesson4.data.model.MyWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("data/2.5/weather?")
    Call<MyWeather> getWeatherByCity(
            @Query("q") String cityName,
            @Query("units") String units,
            @Query("appid") String appid
    );
}
