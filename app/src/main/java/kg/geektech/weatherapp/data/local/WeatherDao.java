package kg.geektech.weatherapp.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import kg.geektech.weatherapp.data.models.WeatherApp;

//В объекте Dao мы будем описывать методы для работы с базой данных.

@Dao

public interface WeatherDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WeatherApp weather);

    @Query("SELECT * FROM weatherapp")
    WeatherApp getWeather();



}
