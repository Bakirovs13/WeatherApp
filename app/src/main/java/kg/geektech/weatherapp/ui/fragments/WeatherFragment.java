package kg.geektech.weatherapp.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.Main;
import kg.geektech.weatherapp.data.models.Sys;
import kg.geektech.weatherapp.data.models.Weather;
import kg.geektech.weatherapp.data.models.WeatherApp;
import kg.geektech.weatherapp.data.models.Wind;
import kg.geektech.weatherapp.databinding.FragmentWeatherBinding;

@AndroidEntryPoint
public class WeatherFragment extends Fragment {

    private WeatherApp weather;
    private Main main;
    private Wind wind;
    private Sys sys;
    FragmentWeatherBinding binding;
    private WeatherViewModel viewModel;
    private ArrayList<Weather> weatherList = new ArrayList<>();
    private WeatherFragmentArgs args;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity())
                .get(WeatherViewModel.class);
        args = WeatherFragmentArgs.fromBundle(getArguments());
        viewModel.getWeather();
        viewModel.getWeatherByCityName(args.getCityName());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<Resource<WeatherApp>>() {
            @Override
            public void onChanged(Resource<WeatherApp> response) {
                switch (response.status){
                    case SUCCESS:{
                        wind = response.data.getWind();
                        weather = response.data;
                        main = response.data.getMain();
                        sys =  response.data.getSys();
                        weatherList = (ArrayList<Weather>) response.data.getWeather();
                        binding.progressBar.setVisibility(View.GONE);
                        setCurrentWeather();

                        break;
                    }
                    case ERROR:{
                        binding.progressBar.setVisibility(View.GONE);
                        Log.e("TAG", "onChanged: "+response.message);
                        break;
                    }
                    case LOADING:{
                        binding.progressBar.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }
        });
    }

    private void initListeners() {
        binding.rectangle.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment_container);
            navController.navigate(R.id.detailFragment);

        });
    }

    @SuppressLint("SetTextI18n")
    private void setCurrentWeather() {
        Glide.with(requireContext())
                .load("https://openweathermap.org/img/wn/" + weatherList.get(0).getIcon() + ".png")
                .override(100, 100)
                .into(binding.weatherStatusImg);

        //sunset, sunrise and daytime
        binding.sunsetTv.setText(getTime(requireContext(), Long.valueOf(sys.getSunset())));
        binding.daytimeTv.setText(getTime(requireContext(), (long) (sys.getSunset() - sys.getSunrise())));
        binding.sunriseTv.setText(getTime(requireContext(), Long.valueOf(sys.getSunrise())));
        //wind
        binding.windTv.setText(wind.getSpeed() + " km/ h");
        //max and min temperature
        binding.maxTempTv.setText(main.getTempMax()+" °C");
        binding.minTempTv.setText(main.getTempMin()+" °C");
        //weather Status(clouds..)
        binding.weatherStatus.setText(weatherList.get(0).getMain());
        //location info
        binding.locationTv.setText(weather.getName());
        //humidity
        binding.HumidityTv.setText(main.getHumidity() + "%");
        //pressure
        binding.pressureTv.setText(main.getPressure() + "mBar");
        //temp Celsius
        binding.tempTv.setText(String.valueOf((int) Math.round(main.getTemp())));
        //date
        binding.dateTv.setText(getDate(System.currentTimeMillis()));

    }

    @SuppressLint("SimpleDateFormat")
    public static String getDate(Long date) {
        return new SimpleDateFormat("EEEE,MMM d | h:mm a").format(date);
    }

    public static String getTime(Context context, Long date) {
        return DateUtils.formatDateTime(context, date * 1000, DateUtils.FORMAT_SHOW_TIME);
    }
}