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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.databinding.FragmentDetailBinding;


@AndroidEntryPoint
public class DetailFragment extends Fragment implements OnMapReadyCallback {

    FragmentDetailBinding binding;
    private GoogleMap mMap;
    NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initListeners();
        navController = Navigation.findNavController(requireActivity(),R.id.fragment_container);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_frag);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

    }

    private void initListeners() {
      //  binding.detailBtn.setOnClickListener(view -> {
          //  NavController navController = Navigation.findNavController(requireActivity(),R.id.fragment_container);
          //  navController.navigate(WeatherFragmentDirections.actionWeatherFragmentToDetailFragment());
         //   String name =binding.cityId.getText().toString();
        //    navController.navigate(DetailFragmentDirections.actionDetailFragmentToWeatherFragment(name));
       // });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(latLng -> {
            MarkerOptions options =new MarkerOptions();
            options.position(latLng);
            mMap.clear();
            mMap.addMarker(options);
            mMap.animateCamera(
                    CameraUpdateFactory.newCameraPosition(
                            CameraPosition.builder()
                            .zoom(10f)
                                    .target(latLng)
                            //.bearing(100)
                           // .tilt(30f)
                            .build()

                    ));

            mMap.setOnMarkerClickListener(marker -> {

                navController.navigate(DetailFragmentDirections
                        .actionDetailFragmentToWeatherFragment(String.valueOf(latLng.latitude),String.valueOf(latLng.longitude)));
                return false;
            });
        });

    }
}