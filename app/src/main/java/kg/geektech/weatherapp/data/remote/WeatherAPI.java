package kg.geektech.weatherapp.data.remote;

import kg.geektech.weatherapp.data.models.Weather;
import kg.geektech.weatherapp.data.models.WeatherApp;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    @GET("weather?")  //endpoint
    Call<WeatherApp> getTemp(   //Call - вовзращаемый тип , Weather - моделька
                                @Query("q") String city,
                                @Query("appid") String appId,
                                @Query("units") String metric
    );

}
