package kg.geektech.weatherapp;

import android.app.Application;

import kg.geektech.weatherapp.data.remote.RetrofitClient;
import kg.geektech.weatherapp.data.remote.WeatherAPI;
import kg.geektech.weatherapp.data.repository.MainRepository;
import retrofit2.Retrofit;

public class App extends Application {

   public static WeatherAPI api;
    public static MainRepository repository;


    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient client = new RetrofitClient();
        api = client.provideAPI();
        repository = new MainRepository();

    }
}
