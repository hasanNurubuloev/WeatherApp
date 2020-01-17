package com.example.weatherapp.ui.splash;

import android.os.Bundle;
import android.util.Log;

import com.example.weatherapp.R;
import com.example.weatherapp.data.entity.forecast.ForecastEntity;
import com.example.weatherapp.data.internet.RetrofitBuilder;
import com.example.weatherapp.ui.base.BaseActivity;
import com.example.weatherapp.ui.main.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.weatherapp.BuildConfig.API_KEY;

public class SplashActivity extends BaseActivity {


    @Override
    protected int getViewLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchForecast();
    }


    private void fetchForecast() {
        RetrofitBuilder.getService().getForecast("Bishkek", API_KEY, "metric")
                .enqueue(new Callback<ForecastEntity>() {
                    @Override
                    public void onResponse(Call<ForecastEntity> call, Response<ForecastEntity> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            MainActivity.startForSplash(getApplicationContext(), response.body());
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastEntity> call, Throwable t) {
                        Log.e("ololo", "onResponse: ");

                    }
                });
//    }
//    private void initLaunch() {
//        if (PreferenceHelper.getIsNotFirstLaunch()) {
//            MainActivity.start(this);
//        } else {
//            OnBoardActivity.start(this);
//            PreferenceHelper.setIsFirstLaunch();
//        }
//
//        finish();
//    }
    }
}
