//Fawad Iqbal Orakzai S2027118
package com.OrakzaiFawadIqbalMPDSecondDiet.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.OrakzaiFawadIqbalMPDSecondDiet.data.models.WeatherData;
import com.OrakzaiFawadIqbalMPDSecondDiet.data.parser.XMLParser;
import com.OrakzaiFawadIqbalMPDSecondDiet.utils.Constants;
import com.OrakzaiFawadIqbalMPDSecondDiet.utils.ToastUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    XMLParser parser = new XMLParser();
    final CompositeDisposable disposables = new CompositeDisposable();
    ToastUtils toastUtils = new ToastUtils();

    HashMap<String, ArrayList<WeatherData>> locationsData = new HashMap<>();
    private final MutableLiveData<Boolean> _uiState = new MutableLiveData<>();
    LiveData<Boolean> uiState = _uiState;

    ArrayList<String> locations = new ArrayList<>();

    public HomeViewModel() {
        getLocations();
        callApi();
    }

    public void getLocations() {
        locations.add(Constants.LOC_GLASGOW);
        locations.add(Constants.LOC_LONDON);
        locations.add(Constants.LOC_NEWYORK);
        locations.add(Constants.LOC_OMAN);
        locations.add(Constants.LOC_MAURITIUS);
        locations.add(Constants.LOC_BANGLADESH);
    }

    public Single<ArrayList<WeatherData>> getWeather(String uri) {
        return Single.create(emitter -> {
            URL url;
            URLConnection yc;
            BufferedReader in;
            String inputLine;
            StringBuilder result = new StringBuilder();
            try {
                url = new URL(Constants.BASE_URL + uri);
                yc = url.openConnection();
                in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                while ((inputLine = in.readLine()) != null) {
                    result.append(inputLine);
                }
                in.close();
                InputStream inputStream = new ByteArrayInputStream(result.toString().getBytes(StandardCharsets.UTF_8));
                emitter.onSuccess(parser.getWeatherData(inputStream, uri));
            } catch (IOException e) {
                emitter.onError(e);
            }
        });
    }

    private void callApi() {
        ArrayList<String> temp = new ArrayList<>(locations);
        for (String location : locations) {
            getWeather(location)
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new SingleObserver<ArrayList<WeatherData>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            toastUtils.showProgressDialog();
                            disposables.add(d);
                        }

                        @Override
                        public void onSuccess(@NonNull ArrayList<WeatherData> weatherData) {
                            locationsData.put(Constants.getLocationFromID(location), weatherData);
                            temp.remove(location);
                            // Update UI only on last call
                            if (temp.isEmpty()) {
                                _uiState.postValue(true);
                                toastUtils.hideProgressDialog();
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            toastUtils.showToast(e.getLocalizedMessage() != null ? e.getLocalizedMessage() : "Something went wrong");
                            toastUtils.hideProgressDialog();
                        }
                    });
        }
    }


    @Override
    protected void onCleared() {
        disposables.dispose();
        super.onCleared();
    }
}
