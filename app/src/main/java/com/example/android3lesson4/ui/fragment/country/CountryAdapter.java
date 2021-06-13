package com.example.android3lesson4.ui.fragment.country;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3lesson4.databinding.ItemCountryBinding;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private final List<String> cities;
    private final LocationClick locationClick;


    public CountryAdapter(List<String> cities, LocationClick locationClick) {
        this.cities = cities;
        this.locationClick = locationClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCountryBinding ui =
                ItemCountryBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(ui);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(cities.get(position));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemCountryBinding ui;

        public ViewHolder(@NonNull ItemCountryBinding ui) {
            super(ui.getRoot());
            this.ui = ui;
            ui.getRoot().setOnClickListener(v -> locationClick.cityClick(cities.get(getAdapterPosition())));
        }

        public void onBind(String city) {
            ui.textTitleItemCountry.setText(city);
        }
    }

    public interface LocationClick {
        void cityClick(String city);
    }
}