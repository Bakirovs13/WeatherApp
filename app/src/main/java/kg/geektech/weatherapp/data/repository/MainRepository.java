package kg.geektech.weatherapp.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import kg.geektech.weatherapp.App;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.WeatherApp;
import kg.geektech.weatherapp.data.remote.WeatherAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private WeatherAPI api;

    @Inject
    public MainRepository(WeatherAPI aPi) {
        this.api = aPi;
    }

    public MutableLiveData<Resource<WeatherApp>> getWeather() {

        MutableLiveData<Resource<WeatherApp>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(Resource.loading());
        api.getTemp("Bishkek", "82ed191a02db835cc3b61d5910def7b0", "metric").enqueue(new Callback<WeatherApp>() {
            @Override
            public void onResponse(@NonNull Call<WeatherApp> call, @NonNull Response<WeatherApp> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mutableLiveData.setValue(Resource.success(response.body()));
                } else {
                    mutableLiveData.setValue(Resource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherApp> call, @NonNull Throwable t) {
                mutableLiveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<Resource<WeatherApp>> getWeatherByCityName(String name) {
        MutableLiveData<Resource<WeatherApp>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getTemp(name,"82ed191a02db835cc3b61d5910def7b0", "metric").enqueue(new Callback<WeatherApp>() {
            @Override
            public void onResponse(Call<WeatherApp> call, Response<WeatherApp> response) {
                if (response.isSuccessful()&&response.body()!= null){
                    liveData.setValue(Resource.success(response.body()));
                }else{
                    liveData.setValue(Resource.error(response.message(),null));

                }
            }

            @Override
            public void onFailure(Call<WeatherApp> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(),null));
            }
        });
        return liveData;
    }
}
