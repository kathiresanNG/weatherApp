package com.example.tech2k8.weatherapp;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class FragmentTwo extends Fragment{

    GoogleMap myMpas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootLayout =inflater.inflate(R.layout.fragment_two,container,false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                myMpas=googleMap;

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        //Toast.makeText(getActivity(), ""+latLng, Toast.LENGTH_SHORT).show();
                        MarkerOptions options =new MarkerOptions();
                        options.position(latLng);
                        options.title("my address");
                        myMpas.addMarker(options);
                    }
                });

                Geocoder geocoder =new Geocoder(getContext(), Locale.getDefault());
                try {
                    List<Address> addresses=geocoder.getFromLocationName("10, PT Rajan Rd, K K Nagar, Sector 3, KK Nagar, Chennai, Tamil Nadu ",2);
                    double lat = addresses.get(0).getLatitude();
                    double lon = addresses.get(0).getLongitude();
                    LatLng slaAddress =new LatLng(lat,lon);
                    googleMap.addMarker(new MarkerOptions().position(slaAddress));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity(), "Map loaded successfully", Toast.LENGTH_SHORT).show();
            }
        });
        return rootLayout;
    }
}
