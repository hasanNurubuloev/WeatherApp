package com.example.weatherapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.data.entity.CurrentWeather;
import com.example.weatherapp.data.internet.RetrofitBuilder;
import com.example.weatherapp.ui.base.BaseActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {


    @BindView(R.id.toolbar_main)
    Toolbar toolbar;

    String city = "Bishkek";

    @BindView(R.id.image_sun)
    ImageView imageView;
    @BindView(R.id.text_location)
    TextView location;
    @BindView(R.id.text_cloudiness)
    TextView cloudiness;
    @BindView(R.id.text_cloudiness_value)
    TextView cloudiness_value;
    @BindView(R.id.text_data)
    TextView data_text;
    @BindView(R.id.text_humidity)
    TextView humidity;
    @BindView(R.id.text_little_cloud)
    TextView little_cloud;
    @BindView(R.id.text_humidity_value)
    TextView humidity_value;
    @BindView(R.id.text_max)
    TextView text_max;
    @BindView(R.id.text_max_gradus)
    TextView max_gradus;
    @BindView(R.id.text_min_gradus)
    TextView min_gradus;
    @BindView(R.id.text_month_and_year)
    TextView month_and_year;
    @BindView(R.id.text_min)
    TextView text_min;
    @BindView(R.id.text_now)
    TextView text_now;
    @BindView(R.id.text_now_gradus)
    TextView now_gradus;
    @BindView(R.id.text_sunset)
    TextView sunset;
    @BindView(R.id.text_sunset_value)
    TextView sunset_value;
    @BindView(R.id.text_wind)
    TextView wind;
    @BindView(R.id.text_wind_value)
    TextView wind_value;
    @BindView(R.id.text_today)
    TextView today;
    @BindView(R.id.text_pressure)
    TextView pressure;
    @BindView(R.id.text_pressure_value)
    TextView pressure_value;
    @BindView(R.id.text_sunrice)
    TextView sunrice;
    @BindView(R.id.text_sunrice_value)
    TextView sunricevalue;


    @Override
    protected int getViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        fetchCurrentWeather();


    }


    private void fetchCurrentWeather() {
        RetrofitBuilder.getService().fetchtCurrentWeather(city, "4d63c1acf9a085448b23971128e5eddd", "metric").
                enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            CurrentWeather data = response.body();
                            if (data != null) {
                                pressure_value.setText(String.valueOf(data.getMain().getPressure()));
                                now_gradus.setText(String.valueOf(data.getMain().getTemp()));
                                max_gradus.setText(String.valueOf(data.getMain().getTempMax()));
                                min_gradus.setText(String.valueOf(data.getMain().getTempMin()));
                                humidity_value.setText(String.valueOf(data.getMain().getHumidity()));
                                wind_value.setText(String.valueOf(data.getWind().getSpeed()));
                                Glide.with(MainActivity.this).load("http://openweathermap.org/img/wn/" + response.body().
                                        getWeather().get(0).getIcon() + "@2x.png").centerCrop().into(imageView);
                                little_cloud.setText(data.getWeather().get(0).getDescription());
                                DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                                Date date1 = new Date();
                                Date date2 = new Date();
                                date1.setTime((long) data.getSys().getSunrise() * 1000);
                                date2.setTime((long) data.getSys().getSunset() * 1000);
                                String daty = dateFormat.format(date1.getTime());
                                String daty1 = dateFormat.format(date2.getTime());
                                sunricevalue.setText(daty);
                                sunset_value.setText(daty1);
                                Calendar cal = Calendar.getInstance();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-YYYY Время:HH:MM");
                                String format = sdf.format(cal.getTime());
                                data_text.setText(format);
                                cloudiness_value.setText(String.valueOf(data.getClouds().getAll()));
                                location.setText(city);
                                fillViews(response.body());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void fillViews(CurrentWeather body) { //todo TextView temp
    }


    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }
}
