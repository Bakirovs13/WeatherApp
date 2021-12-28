package kg.geektech.weatherapp.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import kg.geektech.weatherapp.App;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.WeatherApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    public MutableLiveData<Resource<WeatherApp>> getWeather() {

        MutableLiveData<Resource<WeatherApp>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(Resource.loading());
        App.api.getTemp("Bishkek", "82ed191a02db835cc3b61d5910def7b0", "metric").enqueue(new Callback<WeatherApp>() {
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

}
