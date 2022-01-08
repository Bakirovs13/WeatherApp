package kg.geektech.weatherapp.data.local.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kg.geektech.weatherapp.data.models.Clouds;

public class CloudsConverter {
    @TypeConverter
    public String fromMainString(Clouds main){
        if (main == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Clouds>(){}.getType();
        return gson.toJson(main,type);
    }
    @TypeConverter
    public Clouds fromMainString(String fromString){
        if (fromString == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Clouds>(){}.getType();
        return gson.fromJson(fromString,type);
    }
}
