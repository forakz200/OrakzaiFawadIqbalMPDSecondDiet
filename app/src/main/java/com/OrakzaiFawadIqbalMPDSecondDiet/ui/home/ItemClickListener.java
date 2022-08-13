//Fawad Iqbal Orakzai S2027118
package com.OrakzaiFawadIqbalMPDSecondDiet.ui.home;

import com.OrakzaiFawadIqbalMPDSecondDiet.data.models.WeatherData;

import java.util.ArrayList;

public interface ItemClickListener {
    void onMoreClicked(String locationName, ArrayList<WeatherData> data);
}
