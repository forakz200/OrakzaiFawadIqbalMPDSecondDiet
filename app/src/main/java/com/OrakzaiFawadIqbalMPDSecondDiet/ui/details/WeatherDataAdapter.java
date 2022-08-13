//Fawad Iqbal Orakzai S2027118
package com.OrakzaiFawadIqbalMPDSecondDiet.ui.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.OrakzaiFawadIqbalMPDSecondDiet.R;
import com.OrakzaiFawadIqbalMPDSecondDiet.data.models.WeatherData;
import com.OrakzaiFawadIqbalMPDSecondDiet.ui.home.ItemClickListener;
import com.OrakzaiFawadIqbalMPDSecondDiet.utils.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeatherDataAdapter extends RecyclerView.Adapter<WeatherDataAdapter.ViewHolder> {

    List<WeatherData> data;
    ItemClickListener listener;
    HashMap<String, ArrayList<WeatherData>> map = new HashMap<>();
    boolean showBtn;

    public WeatherDataAdapter(ItemClickListener listener, List<WeatherData> data, boolean showBtn) {
        this.listener = listener;
        this.data = data;
        this.showBtn = showBtn;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather_data, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherData item = data.get(position);

        if (item.getTypeData() != null && item.getTypeData().getBackgroundImage() != -1) {
            holder.parentLayout.setBackgroundResource(item.getTypeData().getBackgroundImage());
            holder.weatherImg.setImageResource(item.getTypeData().getImage());
        }
        holder.dateTitle.setText(TimeUtils.getForecastDate(item.getDate(), position, position != 0));
        holder.dayTitle.setText(item.getTitle());
        holder.todayWeather.setText(item.getTypeData().getWeather());
        holder.visibilityText.setText(item.getVisibility() != null ? "Visibility: " + item.getVisibility() : "--");

        holder.minimumText.setText(item.getMinTemp() != null ? item.getMinTemp() : "--");
        holder.maximumText.setText(item.getMaxTemp() != null ? item.getMaxTemp() : "--");

        holder.sunRiseText.setText(item.getSunrise() != null ? item.getSunrise() : "--");
        holder.sunSetText.setText(item.getSunset() != null ? item.getSunset() : "--");

        holder.textHumidity.setText(item.getHumidity() != null ? item.getHumidity() : "--");
        holder.textPollution.setText(item.getPollution() != null ? item.getPollution() : "--");
        holder.textWindSpeed.setText(item.getWindSpeed() != null ? item.getWindSpeed() : "--");
        holder.textWindDir.setText(item.getWindDirection() != null ? item.getWindDirection() : "--");
        holder.textPressure.setText(item.getPressure() != null ? item.getPressure() : "--");
        holder.textUVRisk.setText(item.getUvRisk() != null ? item.getUvRisk() : "--");

        holder.textLastUpdate.setText(item.getDate() != null ? "Last updated: " + item.getDate() : "--");
        if (showBtn) {
            holder.locationText.setVisibility(View.VISIBLE);
            holder.locationText.setText(item.getLocation());
        } else {
            holder.locationText.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTitle, locationText, dayTitle, todayWeather, visibilityText, minimumText, maximumText, sunRiseText, sunSetText, textHumidity, textPollution, textWindSpeed, textWindDir, textPressure, textUVRisk, textLastUpdate;
        ConstraintLayout parentLayout;
        ImageView weatherImg;

        public ViewHolder(View view) {
            super(view);
            parentLayout = (ConstraintLayout) view.findViewById(R.id.parent_bg);
            dateTitle = (TextView) view.findViewById(R.id.date_title);
            locationText = (TextView) view.findViewById(R.id.location_text);
            dayTitle = (TextView) view.findViewById(R.id.day_title);
            todayWeather = (TextView) view.findViewById(R.id.today_weather);
            visibilityText = (TextView) view.findViewById(R.id.visibility_text);
            weatherImg = (ImageView) view.findViewById(R.id.weather_img);
            minimumText = (TextView) view.findViewById(R.id.minimum_value);
            maximumText = (TextView) view.findViewById(R.id.maximum_value);
            sunRiseText = (TextView) view.findViewById(R.id.sun_rise_text);
            sunSetText = (TextView) view.findViewById(R.id.sun_set_text);
            textHumidity = (TextView) view.findViewById(R.id.text_humidity);
            textPollution = (TextView) view.findViewById(R.id.text_pollution);
            textWindSpeed = (TextView) view.findViewById(R.id.text_wind_speed);
            textWindDir = (TextView) view.findViewById(R.id.text_wind_direction);
            textPressure = (TextView) view.findViewById(R.id.text_pressure);
            textUVRisk = (TextView) view.findViewById(R.id.text_uv_risk);
            textLastUpdate = (TextView) view.findViewById(R.id.last_update_text);
        }
    }

    public void updateList(WeatherData newData) {
        if (data == null) {
            data = new ArrayList<>();
        }
        data.add(newData);
        notifyItemInserted(data.size() - 1);
    }

    public void updateList(ArrayList<WeatherData> newData) {
        if (data == null) {
            data = new ArrayList<>();
        }
        int old = data.size();
        data.addAll(newData);
        notifyItemInserted(old + 1);
    }

    public void setHashMap(HashMap<String, ArrayList<WeatherData>> map) {
        this.map = map;
    }

}
