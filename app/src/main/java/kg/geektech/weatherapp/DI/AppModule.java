package kg.geektech.weatherapp.DI;

import android.content.Context;

import androidx.room.Room;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import kg.geektech.weatherapp.data.local.RoomClient;
import kg.geektech.weatherapp.data.local.WeatherDao;
import kg.geektech.weatherapp.data.local.WeatherDataBase;
import kg.geektech.weatherapp.data.models.WeatherApp;
import kg.geektech.weatherapp.data.remote.WeatherAPI;
import kg.geektech.weatherapp.data.repository.MainRepository;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    public static WeatherAPI provideAPI(Retrofit retrofit){
        return retrofit.create(WeatherAPI.class);
    }

    @Provides
    public static MainRepository provideMainRepo(WeatherAPI aPi,WeatherDao dao){
        return new MainRepository(aPi,dao);
    }


    @Provides
    public static Retrofit provideRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    public static OkHttpClient providesOkHttpClient(Interceptor loggingInterceptor){
        return new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

    }

    @Provides
    public static Interceptor provideLoggingInterceptor() {

        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }





    @Provides
    public static WeatherDataBase provideAppDatabase(@ApplicationContext Context context){

        return Room.databaseBuilder(context,WeatherDataBase.class,"database")
               .allowMainThreadQueries()
                .build();

    }
    @Provides
    public static WeatherDao provideWeatherDao(WeatherDataBase database){
        return database.weatherDao();
    }


}
