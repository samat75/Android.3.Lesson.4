package com.example.android3lesson4.data;

import com.example.android3lesson4.data.model.MyWeather;
import com.example.android3lesson4.data.network.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    public static void getWeatherByCity(String cityName, String units, String appid, WeatherCallback callback) {
        WeatherService.getInstance().getWeatherApi().getWeatherByCity(cityName, units, appid).enqueue(new Callback<MyWeather>() {
            @Override
            public void onResponse(Call<MyWeather> call, Response<MyWeather> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<MyWeather> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }


    public interface WeatherCallback {
        void onSuccess(MyWeather myWeather);

        void onFailure(String message);
    }
}
