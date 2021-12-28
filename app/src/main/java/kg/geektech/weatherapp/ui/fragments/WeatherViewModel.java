package kg.geektech.weatherapp.ui.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import kg.geektech.weatherapp.App;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.Weather;
import kg.geektech.weatherapp.data.models.WeatherApp;
import kg.geektech.weatherapp.data.repository.MainRepository;

public class WeatherViewModel extends ViewModel {


    public LiveData<Resource<WeatherApp>> liveData;

    public WeatherViewModel() {
    }


    public void getWeather() {
        liveData = App.repository.getWeather();
    }
}
