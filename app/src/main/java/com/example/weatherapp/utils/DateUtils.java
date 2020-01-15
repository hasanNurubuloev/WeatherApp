package com.example.weatherapp.utils;

import com.example.weatherapp.data.entity.CurrentWeather;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
private static final String SUNSET_FORMAT = "HH:mm";
private static final String TIME_FORMAT = "dd-MMMM-YYYY Время:HH:MM";

    public static  String  parceSunSet (Integer time) {
        DateFormat dateFormat = new SimpleDateFormat(SUNSET_FORMAT, Locale.getDefault( ));
        Date date = new Date();
        date.setTime((long)time  * 1000);

        return dateFormat.format(date.getTime());
    }
    public static String parseData(CurrentWeather data) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT , Locale.getDefault());
        return sdf.format(cal.getTime());
    }
}
