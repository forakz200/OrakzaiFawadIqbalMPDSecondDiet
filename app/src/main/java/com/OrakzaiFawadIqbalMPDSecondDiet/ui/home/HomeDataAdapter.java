//Fawad Iqbal Orakzai S2027118
package com.OrakzaiFawadIqbalMPDSecondDiet.ui.home;

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
import com.OrakzaiFawadIqbalMPDSecondDiet.utils.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeDataAdapter extends RecyclerView.Adapter<HomeDataAdapter.ViewHolder> {

    List<WeatherData> data;
    ItemClickListener listener;
    HashMap<String, ArrayList<WeatherData>> map = new HashMap<>();

    public HomeDataAdapter(ItemClickListener listener, List<WeatherData> data) {
        this.listener = listener;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        position = position % data.size();
        WeatherData item = data.get(position);

        if (item.getTypeData() != null && item.getTypeData().getBackgroundImage() != -1) {
          //  holder.parentLayout.setBackgroundResource(item.getTypeData().getBackgroundImage());
            holder.weatherImg.setImageResource(item.getTypeData().getImage());
        }

        holder.dateText.setText(TimeUtils.getForecastDate(item.getDate(),0,false));
        holder.locationTitle.setText(item.getLocation());
        holder.todayWeather.setText(item.getTypeData().getWeather());
        holder.visibilityText.setText(item.getVisibility() != null ? "Visibility: " + item.getVisibility() : "--");

        holder.minimumText.setText(item.getMinTemp() != null ? item.getMinTemp() : "--");
        holder.maximumText.setText(item.getMaxTemp() != null ? item.getMaxTemp() : "--");
        holder.parentLayout.setOnClickListener(l -> listener.onMoreClicked(item.getLocation(), map.get(item.getLocation())));
    }

    @Override
    public int getItemCount() {
        return data.size() * 2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView locationTitle,dateText, todayWeather, visibilityText, minimumText, maximumText;
        ConstraintLayout parentLayout;
        ImageView weatherImg;

        public ViewHolder(View view) {
            super(view);
            parentLayout = (ConstraintLayout) view.findViewById(R.id.parent_bg);
            dateText = (TextView) view.findViewById(R.id.date_text);
            locationTitle = (TextView) view.findViewById(R.id.location_title);
            todayWeather = (TextView) view.findViewById(R.id.today_weather);
            visibilityText = (TextView) view.findViewById(R.id.visibility_text);
            weatherImg = (ImageView) view.findViewById(R.id.weather_img);
            minimumText = (TextView) view.findViewById(R.id.minimum_value);
            maximumText = (TextView) view.findViewById(R.id.maximum_value);
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

