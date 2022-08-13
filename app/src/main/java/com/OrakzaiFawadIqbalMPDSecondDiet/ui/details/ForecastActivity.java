//Fawad Iqbal Orakzai S2027118
package com.OrakzaiFawadIqbalMPDSecondDiet.ui.details;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.OrakzaiFawadIqbalMPDSecondDiet.R;
import com.OrakzaiFawadIqbalMPDSecondDiet.data.models.WeatherData;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator2;

public class ForecastActivity extends AppCompatActivity {

    private static final String WEATHER_DATA = "WEATHER_DATA";
    private static Intent newInstance;
    ForecastViewModel viewModel;

    RecyclerView weatherRecyclerView;
    WeatherDataAdapter adapter;
    CircleIndicator2 circleIndicator;
    ImageView backBtn;
    TextView titleText;

    public static Intent newIntent(Context context, ArrayList<WeatherData> data) {
        if (newInstance == null) {
            newInstance = new Intent(context, ForecastActivity.class);
        }
        newInstance.putParcelableArrayListExtra(WEATHER_DATA, data);
        return newInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ForecastViewModel.class);
        setContentView(R.layout.activity_weather_details);

        initViews();
        init();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initViews() {
        weatherRecyclerView = findViewById(R.id.weather_recyclerview);
        circleIndicator = findViewById(R.id.circle_indicator);
        backBtn = findViewById(R.id.back_btn);
        titleText = findViewById(R.id.title_text);

        backBtn.setOnClickListener(l -> onBackPressed());
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        if (viewModel.data.isEmpty())
            viewModel.data = getIntent().getParcelableArrayListExtra(WEATHER_DATA);
        titleText.setText(String.format(getString(R.string.day_forecast), viewModel.data.get(0).getLocation()));
        initRecyclerView();
    }

    void initRecyclerView() {
        if (adapter == null) {
            adapter = new WeatherDataAdapter(null, new ArrayList<>(), false);
            weatherRecyclerView.setLayoutManager(
                    new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            );
            weatherRecyclerView.setAdapter(adapter);
            adapter.updateList(viewModel.data);

            PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
            pagerSnapHelper.attachToRecyclerView(weatherRecyclerView);

            circleIndicator.attachToRecyclerView(weatherRecyclerView, pagerSnapHelper);
        }
    }

    @Override
    protected void onDestroy() {
        adapter = null;
        super.onDestroy();
    }

}