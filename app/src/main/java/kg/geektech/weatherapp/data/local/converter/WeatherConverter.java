package kg.geektech.weatherapp.data.local.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import kg.geektech.weatherapp.data.models.Weather;


public class WeatherConverter {

    @TypeConverter
    public String fromMainString(List<Weather> main){
        if (main == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather>>(){}.getType();
        return gson.toJson(main,type);
    }
    @TypeConverter
    public List<Weather> fromMainString(String fromString){
        if (fromString == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather>>(){}.getType();
        return gson.fromJson(fromString,type);
    }
}
