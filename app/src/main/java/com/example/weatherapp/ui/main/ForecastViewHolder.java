package com.example.weatherapp.ui.main;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.data.entity.current.CurrentWeather;

public class ForecastViewHolder extends RecyclerView.ViewHolder {
    TextView tvMinTemp, tvMaxTemp;

    public ForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        tvMinTemp = itemView.findViewById(R.id.tvMinTemp);
        tvMaxTemp = itemView.findViewById(R.id.tvMaxTemp);
    }

    public void bind(CurrentWeather currentWeather) {


        tvMinTemp.setText(String.format(itemView.getResources().getString(R.string.celcium),
                currentWeather.getMain().getTempMin() ));

        tvMaxTemp.setText(String.format(itemView.getResources().getString(R.string.celcium),
                currentWeather.getMain().getTempMax() ));   }
}
