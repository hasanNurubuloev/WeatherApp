package com.example.weatherapp.data.internet;

import com.example.weatherapp.data.entity.current.CurrentWeather;
import com.example.weatherapp.data.entity.forecast.ForecastEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.weatherapp.data.internet.ApiEndPoints.FORECAST;

public interface RetrofitService {
    @GET(FORECAST)
Call<CurrentWeather>fetchtCurrentWeather(@Query("q") String city,
                                         @Query("appid") String appid,
                                         @Query("units") String metric);

    @GET(FORECAST)
    Call<ForecastEntity> getForecast(@Query("q") String city,
                                     @Query("appid") String appid,
                                     @Query("units") String inits);


}
