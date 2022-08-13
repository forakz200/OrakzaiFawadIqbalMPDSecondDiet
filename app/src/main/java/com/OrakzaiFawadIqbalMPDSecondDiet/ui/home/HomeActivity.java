//Fawad Iqbal Orakzai S2027118
package com.OrakzaiFawadIqbalMPDSecondDiet.ui.home;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.OrakzaiFawadIqbalMPDSecondDiet.R;
import com.OrakzaiFawadIqbalMPDSecondDiet.data.models.WeatherData;
import com.OrakzaiFawadIqbalMPDSecondDiet.ui.details.ForecastActivity;
import com.OrakzaiFawadIqbalMPDSecondDiet.utils.Constants;

import java.util.ArrayList;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements ItemClickListener {

    private Dialog progressDialog;
    HomeViewModel viewModel;

    /**
     * Views
     **/
    RecyclerView citiesRecyclerview;
    HomeDataAdapter adapter;
    LinearLayoutManager layoutManager;
    ImageView downloadBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        setContentView(R.layout.activity_home);

        initViews();
        init();
    }

    private void initViews() {
        citiesRecyclerview = findViewById(R.id.cities_recyclerview);
    }

    protected void init() {
        viewModel.toastUtils.initToastObserver(this);
        initRecyclerView();
        observeData();
    }

    private void observeData() {
        viewModel.toastUtils.progressDialogMessageLiveData.observe(this, showLoader -> {
            if (!showLoader) {
                progressDialog = viewModel.toastUtils.onShowProgressDialog(this, progressDialog, false);
                return;
            }
            progressDialog =
                    viewModel.toastUtils.onShowProgressDialog(this, progressDialog, true);
        });

        viewModel.uiState.observe(this, data -> addDataInSequence());
    }

    void addDataInSequence() {
        adapter.setHashMap(viewModel.locationsData);
        adapter.updateList(Objects.requireNonNull(viewModel.locationsData.get(Constants.getLocationFromID(Constants.LOC_GLASGOW))).get(0));
        adapter.updateList(Objects.requireNonNull(viewModel.locationsData.get(Constants.getLocationFromID(Constants.LOC_LONDON))).get(0));
        adapter.updateList(Objects.requireNonNull(viewModel.locationsData.get(Constants.getLocationFromID(Constants.LOC_NEWYORK))).get(0));
        adapter.updateList(Objects.requireNonNull(viewModel.locationsData.get(Constants.getLocationFromID(Constants.LOC_OMAN))).get(0));
        adapter.updateList(Objects.requireNonNull(viewModel.locationsData.get(Constants.getLocationFromID(Constants.LOC_MAURITIUS))).get(0));
        adapter.updateList(Objects.requireNonNull(viewModel.locationsData.get(Constants.getLocationFromID(Constants.LOC_BANGLADESH))).get(0));
        citiesRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstItemVisible = layoutManager.findFirstVisibleItemPosition();
                if (firstItemVisible != 0 && firstItemVisible % adapter.data.size() == 0) {
                    Objects.requireNonNull(recyclerView.getLayoutManager()).scrollToPosition(0);
                }
            }
        });
    }

    void initRecyclerView() {
        if (adapter == null) {
            adapter = new HomeDataAdapter(this, new ArrayList<>());
            layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            citiesRecyclerview.setLayoutManager(layoutManager);
            citiesRecyclerview.setAdapter(adapter);
        }
    }

    @Override
    public void onMoreClicked(String locationId, ArrayList<WeatherData> data) {
        startActivity(ForecastActivity.newIntent(this, data));
    }

    @Override
    protected void onDestroy() {
        progressDialog = null;
        adapter = null;
        layoutManager = null;
        super.onDestroy();
    }
}
