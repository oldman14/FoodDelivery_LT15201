package com.example.fooddelivery_lt152011.ProductScreen;

import android.annotation.SuppressLint;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.fooddelivery_lt152011.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class InforOderFragment extends Fragment implements OnMapReadyCallback{
    private GoogleMap mMap;
    private Geocoder geocoder;
    LocationRequest locationRequest;
    FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view= inflater.inflate( R.layout.fragment_infor_oder, container, false );
        SupportMapFragment mapFragment= (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById( R.id.map );
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(500);
        locationRequest.setFastestInterval(500);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        mMap.setMapType( GoogleMap.MAP_TYPE_NORMAL );

        @SuppressLint("MissingPermission") Task<Location> locationTask=fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener( new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(latLng, 20));
                mMap.addMarker(new MarkerOptions().position(latLng));
            }
        } );
    }
}