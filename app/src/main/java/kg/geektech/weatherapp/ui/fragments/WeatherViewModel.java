package kg.geektech.weatherapp.ui.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.weatherapp.App;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.Weather;
import kg.geektech.weatherapp.data.models.WeatherApp;
import kg.geektech.weatherapp.data.repository.MainRepository;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private MainRepository repository;
    public LiveData<Resource<WeatherApp>> liveData;

    public WeatherViewModel() {
    }

    @Inject
    public WeatherViewModel(MainRepository repository) {
        this.repository = repository;
    }

    public void getWeather() {
        liveData = repository.getWeather();
    }

    public void getWeatherByCityName(String name){
        liveData = repository.getWeatherByCityName(name);
    }
}
