package com.example.weatherapp.ui.main;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.data.entity.current.CurrentWeather;
import com.example.weatherapp.data.entity.forecast.ForecastEntity;
import com.example.weatherapp.data.internet.RetrofitBuilder;
import com.example.weatherapp.ui.base.BaseActivity;
import com.example.weatherapp.utils.DateUtils;

import java.util.Objects;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.weatherapp.BuildConfig.API_KEY;

public class MainActivity extends BaseActivity {


    private static final String WEATHER = "weather";
    @BindView(R.id.toolbar_main)
    Toolbar toolbar;

    String city;

    @BindView(R.id.image_sun)
    ImageView imageView;
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

    private Spinner spinner;
    private CurrentWeather data;
    public static final String WEATHER_DATA = "weather";
    private RecyclerView recyclerView;
    private ForecastAdapter adapter;


    @Override
    protected int getViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        setSpinner();
        fetchCurrentWeather();
        initRecycler();
        getData();
    }

    public static void startForSplash(Context context, ForecastEntity forecastEntity) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(WEATHER, forecastEntity);
        context.startActivity(intent);

    }

    public void setSpinner() {
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = (String) parent.getItemAtPosition(position);
                fetchCurrentWeather();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner.setOnItemSelectedListener(listener);
    }

    private void fetchCurrentWeather() {
        RetrofitBuilder.getService().fetchtCurrentWeather(city, API_KEY, "metric").
                enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            data = response.body();
                            changeValues(data);
                            timeAndData();
                            glide(response);
                        } else {
                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void changeValues(CurrentWeather data) {
        pressure_value.setText(String.valueOf(data.getMain().getPressure()));
        now_gradus.setText(String.valueOf(data.getMain().getTemp()));
        max_gradus.setText(String.valueOf(data.getMain().getTempMax()));
        min_gradus.setText(String.valueOf(data.getMain().getTempMin()));
        humidity_value.setText(String.valueOf(data.getMain().getHumidity()));
        wind_value.setText(String.valueOf(data.getWind().getSpeed()));
        cloudiness_value.setText(String.valueOf(data.getClouds().getAll()));
        little_cloud.setText(data.getWeather().get(0).getDescription());

    }

    private void timeAndData() {
        sunricevalue.setText(DateUtils.parceSunSet(data.getSys().getSunrise()));
        sunset_value.setText(DateUtils.parceSunSet(data.getSys().getSunset()));
        data_text.setText(DateUtils.parseData(data));
    }

    public void glide(Response<CurrentWeather> response) {
        Glide.with(MainActivity.this).load("http://openweathermap.org/img/wn/" + response.body().
                getWeather().get(0).getIcon() + "@2x.png").centerCrop().into(imageView);

    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.update_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.update:
                fetchCurrentWeather();
                timeAndData();

        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ForecastAdapter();
        recyclerView.setAdapter(adapter);
    }
    private void getData() {
        Intent intent = getIntent();
        ForecastEntity forecastEntity = (ForecastEntity) intent.getSerializableExtra(WEATHER_DATA);
            adapter.update(forecastEntity.getForecastWeatherList());

    }
}
