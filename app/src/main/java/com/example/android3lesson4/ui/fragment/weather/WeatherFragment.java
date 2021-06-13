package com.example.android3lesson4.ui.fragment.weather;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.android3lesson4.R;
import com.example.android3lesson4.base.BaseFragment;
import com.example.android3lesson4.data.WeatherRepository;
import com.example.android3lesson4.data.model.MyWeather;
import com.example.android3lesson4.data.model.Weather;
import com.example.android3lesson4.databinding.FragmentWeatherBinding;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {

    protected String cityName = "Bishkek";
    protected String units = "metric";
    protected String appid = "e559a400c948f79213fb28d2118e5d19";
    protected WeatherAdapter adapter;


    @Override
    protected FragmentWeatherBinding bind() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null) {
            cityName = getArguments().getString("city");
            getWeather();
        }
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        getWeather();
        setupListener();
    }

    private void setupListener() {
        binding.textCityFragmentWeather.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.action_weatherFragment_to_countryFragment);
        });
    }

    @SuppressLint("NewApi")
    private void init() {
        adapter = new WeatherAdapter();
        binding.recyclerWeatherFragmentWeather.setAdapter(adapter);
    }

    private void getWeather() {
        WeatherRepository.getWeatherByCity(cityName, units, appid, new WeatherRepository.WeatherCallback() {
            @Override
            public void onSuccess(MyWeather myWeather) {
                showModel(myWeather);
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void showModel(MyWeather model) {
        String date = DateFormat.getDateTimeInstance().format(new Date());
        binding.textDateFragmentWeather.setText(date);

        String cityName = model.getName();
        String countryName = model.getSys().getCountry();
        binding.textCityFragmentWeather.setText(cityName + " " + countryName + "  ");

        ArrayList<Weather> cloud = (ArrayList<Weather>) model.getWeather();
        Weather weather = cloud.get(0);
        binding.textCloudFragmentWeather.setText(String.valueOf(weather.getMain()));

        String iconCode = weather.getIcon();
        String iconUrl = "http://openweathermap.org/img/w/" + iconCode + ".png";
        Glide.with(requireActivity()).load(iconUrl).into(binding.imageCloudFragmentWeather);

        Double fahrenheit = model.getMain().getTemp();
        binding.textCelsiusAmountFragmentWeather.setText(String.valueOf(fahrenheit));

        Double fahrenheitUp = model.getMain().getTempMax();
        binding.textCelsiusUpFragmentWeather.setText(String.valueOf(fahrenheitUp));

        Double fahrenheitDown = model.getMain().getTempMin();
        binding.textCelsiusDownFragmentWeather.setText(String.valueOf(fahrenheitDown));

        Integer humidity = model.getMain().getHumidity();
        binding.textPercentHumidityFragmentWeather.setText(humidity + "%");

        Integer pressure = model.getMain().getPressure();
        binding.textMBarPressureFragmentWeather.setText(pressure + "mBar");

        Double wind = model.getWind().getSpeed();
        binding.textKmHWindFragmentWeather.setText(wind + "km/h");

        int sunriseSecs = model.getSys().getSunrise();
        @SuppressLint("SimpleDateFormat")
        String dateRise = new java.text.SimpleDateFormat("hh:mm a").format(new java.util.Date(sunriseSecs * 1000));
        binding.textSunRiseTimeFragmentWeather.setText(dateRise);

        int sunsetSecs = model.getSys().getSunset();
        @SuppressLint("SimpleDateFormat")
        String dateSet = new java.text.SimpleDateFormat("hh:mm a").format(new java.util.Date(sunsetSecs * 1000));
        binding.textSunSetTimeFragmentWeather.setText(dateSet);

    }
}