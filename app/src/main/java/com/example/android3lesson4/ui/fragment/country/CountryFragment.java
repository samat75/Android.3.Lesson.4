package com.example.android3lesson4.ui.fragment.country;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.android3lesson4.R;
import com.example.android3lesson4.base.BaseFragment;
import com.example.android3lesson4.databinding.FragmentCountryBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CountryFragment extends BaseFragment<FragmentCountryBinding> {

    @Override
    protected FragmentCountryBinding bind() {
        return FragmentCountryBinding.inflate(getLayoutInflater());
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<String> cityList = List.of(
                "Bishkek", "London",
                "Moscow", "Miami",
                "Montreal", "Munich",
                "Muscat", "Manila",
                "Tokyo", "Dubai",
                "Barcelona", "Rome",
                "Chicago", "Toronto");
        binding.recyclerViewCountryFragmentCountry.setAdapter(new CountryAdapter(cityList, city -> {
            Bundle bundle = new Bundle();
            bundle.putString("city", city);

            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.weatherFragment, bundle);

        }));
    }
}