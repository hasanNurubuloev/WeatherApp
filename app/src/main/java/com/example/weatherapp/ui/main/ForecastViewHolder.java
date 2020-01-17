package com.example.weatherapp.ui.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.data.entity.current.CurrentWeather;
import com.example.weatherapp.utils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;

public class ForecastViewHolder extends RecyclerView.ViewHolder {
    TextView tvMinTemp, tvMaxTemp, date;
    ImageView imgForecast;


    public ForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        tvMinTemp = itemView.findViewById(R.id.tvMinTemp);
        tvMaxTemp = itemView.findViewById(R.id.tvMaxTemp);
        date = itemView.findViewById(R.id.text__forecast_date);
        imgForecast = itemView.findViewById(R.id.img_forecast);
    }

    public void bind(ArrayList<CurrentWeather> currentWeatherList)  {
        String data = currentWeatherList.get(0).getDateTimeForCast();
        tvMaxTemp.setText(currentWeatherList.get(0).getMain().getTempMax().toString());
        tvMinTemp.setText(currentWeatherList.get(0).getMain().getTempMin().toString());
        try {
            date.setText(DateUtils.forCastDate(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Glide.with(itemView).load("http://openweathermap.org/img/wn/" + currentWeatherList
                .get(0).getWeather().get(0).getIcon() + "@2x.png").into(imgForecast);

    }
}
