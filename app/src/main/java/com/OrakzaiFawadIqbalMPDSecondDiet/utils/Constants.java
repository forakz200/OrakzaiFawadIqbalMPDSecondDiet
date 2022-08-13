//Fawad Iqbal Orakzai S2027118
package com.OrakzaiFawadIqbalMPDSecondDiet.utils;

import com.OrakzaiFawadIqbalMPDSecondDiet.R;
import com.OrakzaiFawadIqbalMPDSecondDiet.data.models.WeatherTypeData;

public class Constants {

    //Parameters from the API
    public static final String ITEM = "item";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String DATE = "dc:date";
    public static final String POINT = "georss:point";

    // Breaking up the "description"
    public static final String MAX_TEMP = "maximum temperature";
    public static final String MIN_TEMP = "minimum temperature";
    public static final String WIND_DIR = "wind direction";
    public static final String WIND_SPEED = "wind speed";
    public static final String VISIBILITY = "visibility";
    public static final String PRESSURE = "pressure";
    public static final String HUMIDITY = "humidity";
    public static final String UV_RISK = "uv risk";
    public static final String POLLUTION = "pollution";
    public static final String SUNRISE = "sunrise";
    public static final String SUNSET = "sunset";

    //NETWORK URIS
    public static final String BASE_URL = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/";
    public static final String LOC_GLASGOW = "2648579";
    public static final String LOC_LONDON = "2643743";
    public static final String LOC_NEWYORK = "5128581";
    public static final String LOC_OMAN = "287286";
    public static final String LOC_MAURITIUS = "934154";
    public static final String LOC_BANGLADESH = "1185241";


    public static String getLocationFromID(String locID) {
        String result = "";
        switch (locID) {
            case LOC_GLASGOW: {
                result = "Glasgow";
                break;
            }
            case LOC_LONDON: {
                result = "London";
                break;
            }
            case LOC_NEWYORK: {
                result = "New York";
                break;
            }
            case LOC_OMAN: {
                result = "Oman";
                break;
            }
            case LOC_MAURITIUS: {
                result = "Mauritius";
                break;
            }
            case LOC_BANGLADESH: {
                result = "Bangladesh";
                break;
            }
        }
        return result;
    }

    public static WeatherTypeData getWeatherData(Boolean isTonight, String weather) {
        WeatherTypeData data = new WeatherTypeData();
        data.setWeather(weather);

        if (weather.toLowerCase().contains("sun") || weather.toLowerCase().contains("clear")) {
            if (!isTonight) {
                data.setImage(R.drawable.img_sunny);
                data.setBackgroundImage(R.drawable.sunny_gradient);
            } else {
                data.setImage(R.drawable.img_moon);
                data.setBackgroundImage(R.drawable.moon_gradient);
            }
        } else if (weather.toLowerCase().contains("storm") || weather.toLowerCase().contains("thunder")) {
            data.setImage(R.drawable.img_storm);
            data.setBackgroundImage(R.drawable.storm_gradient);
        } else if (weather.toLowerCase().contains("rain") || weather.toLowerCase().contains("drizzle")) {
            data.setImage(R.drawable.img_rain);
            data.setBackgroundImage(R.drawable.rainy_gradient);
        } else if (weather.toLowerCase().contains("cloud") || weather.toLowerCase().contains("shower")) {
            data.setImage(R.drawable.img_cloudy);
            data.setBackgroundImage(R.drawable.cloudy_gradient);
        } else if (weather.toLowerCase().contains("snow")) {
            data.setImage(R.drawable.img_snow);
            data.setBackgroundImage(R.drawable.snow_gradient);
        } else if (weather.toLowerCase().contains("haze")) {
            data.setImage(R.drawable.img_haze);
            data.setBackgroundImage(R.drawable.haze_gradient);
        }
        return data;
    }

}
