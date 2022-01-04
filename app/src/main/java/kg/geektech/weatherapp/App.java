package kg.geektech.weatherapp;

import android.app.Application;

import androidx.room.Room;

import dagger.hilt.android.HiltAndroidApp;
import kg.geektech.weatherapp.data.local.WeatherDataBase;

@HiltAndroidApp
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    }

}
