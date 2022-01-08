package kg.geektech.weatherapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import kg.geektech.weatherapp.data.local.converter.CloudsConverter;
import kg.geektech.weatherapp.data.local.converter.MainConverter;
import kg.geektech.weatherapp.data.local.converter.SysConverter;
import kg.geektech.weatherapp.data.local.converter.WeatherConverter;
import kg.geektech.weatherapp.data.local.converter.WindConverter;
import kg.geektech.weatherapp.data.models.WeatherApp;

//Аннотацией Database помечаем основной класс по работе с базой данных.
//В параметрах аннотации Database указываем, какие Entity будут использоваться, и версию базы

@Database(entities = {WeatherApp.class}, version = 1)
@TypeConverters({MainConverter.class, WindConverter.class, SysConverter.class,
        WeatherConverter.class, CloudsConverter.class})

public  abstract class WeatherDataBase  extends RoomDatabase {


    public abstract WeatherDao weatherDao();

}
