package com.example.fooddelivery_lt152011.MyOrder;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.fooddelivery_lt152011.LoginScreen.ModelUser;
import com.example.fooddelivery_lt152011.LoginScreen.SendOTPActivity;
import com.example.fooddelivery_lt152011.LoginScreen.UserDAO;
import com.example.fooddelivery_lt152011.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class InforOderFragment extends Fragment {
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    SupportMapFragment supportMapFragment;
    double lattitude, longtitude;
    Thread thread;
    GoogleMap mMap;
    Marker marker;
    ShipperDAO shipperDAO;
    UserDAO dao;
    OrderDAO orderDAO;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_infor_oder, container, false );
        dao = new UserDAO();
        orderDAO = new OrderDAO();
        shipperDAO = new ShipperDAO( );
        ModelUser nameimg = dao.getUserNames( Integer.parseInt( SendOTPActivity.phone ) );
        Log.d( "LogOrrder", "onCreateView: " + nameimg.getUserID() );
        ModelOrder itemorder = orderDAO.getItemOrder( nameimg.getUserID() );
        Log.d( "LogOrrder", "onCreateView: " + itemorder.getShipID() );
        ModelShipper getship = shipperDAO.getShips( itemorder.getShipID() );

        //view Map
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById( R.id.map );
        ///check perssion location
        ActivityCompat.requestPermissions( getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION );


        locationManager = (LocationManager) getActivity().getSystemService( Context.LOCATION_SERVICE );
        //check GPS bật chưa
        if (!locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER )) {
            OnGPS();
        } else {
            //bật GPS
            getLocation();
        }
        return view;
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission( getContext(), Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission( getContext(), Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION );

        } else {
         Location LocationGPS = locationManager.getLastKnownLocation( LocationManager.GPS_PROVIDER );
            Location LocationNetwork = locationManager.getLastKnownLocation( LocationManager.NETWORK_PROVIDER );
            Location Locationpassive = locationManager.getLastKnownLocation( LocationManager.PASSIVE_PROVIDER );

            if (LocationGPS != null) {
                double lat = LocationGPS.getLatitude();
                double longi = LocationGPS.getLongitude();
                lattitude = lat;
                longtitude = longi;
                Log.d( "Location Users: ", "Location User: " +lattitude+""+longtitude);
            } else if (LocationNetwork != null) {
                double lat = LocationNetwork.getLatitude();
                double longi = LocationNetwork.getLongitude();
                lattitude = lat;
                longtitude = longi;
                Log.d( "Location Users: ", "Location User: " +lattitude+""+longtitude);
            } else if (Locationpassive != null) {
                double lat = Locationpassive.getLatitude();
                double longi = Locationpassive.getLongitude();
                lattitude = lat;
                longtitude = longi;
                Log.d( "Location Users: ", "Location User: " +lattitude+""+longtitude);
            }
        }
        supportMapFragment.getMapAsync( new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.getUiSettings().setMapToolbarEnabled( false );
                mMap.setMapType( GoogleMap.MAP_TYPE_NORMAL );
                mMap.getUiSettings().isMapToolbarEnabled();
                //user
                LatLng latLng = new LatLng( lattitude, longtitude );
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position( latLng );
                markerOptions.title( "Vị trí của bạn" );
                mMap.animateCamera( CameraUpdateFactory.newLatLngZoom( latLng, 16 ) );
                mMap.addMarker( markerOptions );
                dao = new UserDAO(  );
                orderDAO = new OrderDAO( );
                shipperDAO = new ShipperDAO( );
                ModelUser nameimg = dao.getUserNames( Integer.parseInt( SendOTPActivity.phone ) );
                Log.d( "LogOrrder", "onCreateView: " + nameimg.getUserID() );
                ModelOrder itemorder = orderDAO.getItemOrder( nameimg.getUserID() );
                Log.d( "LogOrrder", "onCreateView: " + itemorder.getShipID() );

                new Timer().scheduleAtFixedRate(new TimerTask(){
                    @Override
                    public void run(){
                        AppCompatActivity activity = (AppCompatActivity) getContext();
                        if(activity == null)
                            return;
                        activity.runOnUiThread( new Runnable(){
                            public void run(){
                                //ship
                                ModelShipper getship = shipperDAO.getShips( itemorder.getShipID() );
                            //    MarkerOptions markership=new MarkerOptions();
                                LatLng lngship=new LatLng( getship.getShipLat(),getship.getShipLong() );
//                                markership.position( lngship );
//                                markership.title( "Tài Xế" );
//                                markership.icon( BitmapDescriptorFactory.fromResource( R.drawable.ship ) );
//                                mMap.addMarker( markership );

                                if (marker == null) {
                                    //Create a new marker
                                    MarkerOptions markership = new MarkerOptions();
                                    markership.position(lngship);
                                    markership.icon(BitmapDescriptorFactory.fromResource( R.drawable.ship));
                                  //  markerOptions.rotation(location.getBearing());
                                    markership.anchor((float) 0.5, (float) 0.5);
                                    marker = mMap.addMarker(markership);
                                  //  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                                } else  {
                                    marker.setPosition(lngship);
                                 //   userLocationMarker.setRotation(location.getBearing());
                                  //  mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                                }
                            }
                        });
                    }
                },0,3000);
            }
        } );
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder( getContext() );
        builder.setMessage( "BẬT ĐỊNH VỊ GPS" ).setCancelable( false ).setPositiveButton( "ĐỒNG Ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity( new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS ) );
            }
        } ).setNegativeButton( "KHÔNG", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        } );
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        ;
    }
}