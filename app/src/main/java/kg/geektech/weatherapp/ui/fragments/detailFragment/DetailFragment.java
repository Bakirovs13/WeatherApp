package kg.geektech.weatherapp.ui.fragments.detailFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.databinding.FragmentDetailBinding;


public class DetailFragment extends Fragment {

    FragmentDetailBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
    }

    private void initListeners() {
        binding.detailBtn.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(requireActivity(),R.id.fragment_container);
          //  navController.navigate(WeatherFragmentDirections.actionWeatherFragmentToDetailFragment());
            String name =binding.cityId.getText().toString();
            navController.navigate(DetailFragmentDirections.actionDetailFragmentToWeatherFragment(name));
        });
    }
}