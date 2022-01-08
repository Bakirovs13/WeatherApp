package kg.geektech.weatherapp.data.local.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kg.geektech.weatherapp.data.models.Main;

public class MainConverter {


    @TypeConverter
    public String fromMainString(Main main){
        if (main == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Main>(){}.getType();
        return gson.toJson(main,type);
    }
    @TypeConverter
    public Main fromMainString(String fromString){
        if (fromString == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Main>(){}.getType();
        return gson.fromJson(fromString,type);
    }
}
