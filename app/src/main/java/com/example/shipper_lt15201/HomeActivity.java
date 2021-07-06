package com.example.shipper_lt15201;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shipper_lt15201.Fragment.AccoutFragment;
import com.example.shipper_lt15201.Fragment.NoLive_Fragment;
import com.example.shipper_lt15201.Fragment.OrderFragment;
import com.example.shipper_lt15201.LoginScreen.ModelShipper;
import com.example.shipper_lt15201.LoginScreen.ModelStatusShipper;
import com.example.shipper_lt15201.LoginScreen.SendOTPActivity;
import com.example.shipper_lt15201.LoginScreen.ShipperDAO;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.shipper_lt15201.R.drawable.ic_baseline_brightness_1_24;
import static com.example.shipper_lt15201.R.drawable.ic_live;

public class HomeActivity extends AppCompatActivity {
    int LOCATION_REQUEST_CODE = 10001;
    private static final String TAG = "MainActivity";
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    public static BottomNavigationView bottomNavigationView;
    public static Location shipperLocation;
     public static SwitchCompat btnswitch;
     public static    ImageView imgOnOff;
     public  static Toolbar toolbar;
     public static TextView txtOnOff;
     ShipperDAO dao;
     //location callback
     LocationCallback locationCallback=new LocationCallback(){
         @Override
         public void onLocationResult(LocationResult locationResult) {
             if (locationResult==null){
                 return;
             }
             for (Location location:locationResult.getLocations()){
                 Log.d( TAG, "onLocationResult: "+location.getLatitude() );
                 Log.d( TAG, "onLocationResult: "+location.getLongitude() );
                 ModelStatusShipper locationship=dao.updateLocationShip( Integer.parseInt( SendOTPActivity.phone ),location.getLatitude(),location.getLongitude() );
                 shipperLocation=location;
             }
         }
     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );
        toolbar=findViewById( R.id.toolbar );
        bottomNavigationView = findViewById(R.id.navigation);
        btnswitch=findViewById( R.id.btnswith );
        imgOnOff=findViewById( R.id.imgOnOff );
        txtOnOff=findViewById( R.id.txtOnOff );
        dao=new ShipperDAO( HomeActivity.this );

        ModelShipper update=dao.getShipperName( Integer.parseInt( SendOTPActivity.phone ) );

        if (update.getStatus().equals( "Trực Tuyến" )){
            bottomNavigationView.setOnNavigationItemSelectedListener( mOnNavigation );
            loadFragment( new OrderFragment() );
            btnswitch.setChecked( true );
            imgOnOff.setImageDrawable( getResources().getDrawable( ic_live ) );
            txtOnOff.setText( "Trực Tuyến" );


            fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient( HomeActivity.this );
            locationRequest=LocationRequest.create();
            locationRequest.setInterval( 4000 );
            locationRequest.setFastestInterval( 2000 );

            locationRequest.setPriority( LocationRequest.PRIORITY_HIGH_ACCURACY );



        }
        if (update.getStatus().equals( "Ngoại Tuyến" )){
            bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigation1);
            loadFragment( new NoLive_Fragment() );
            btnswitch.setChecked( false );
            imgOnOff.setImageDrawable( getResources().getDrawable( ic_baseline_brightness_1_24 ) );
            txtOnOff.setText( "Ngoại Tuyến" );
        }

        btnswitch.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    bottomNavigationView.setOnNavigationItemSelectedListener( mOnNavigation );
                    loadFragment( new OrderFragment() );
                    imgOnOff.setImageDrawable( getResources().getDrawable( ic_live ) );
                    txtOnOff.setText( "Trực Tuyến" );
                    ModelStatusShipper shipstatus=dao.updatestatusShip( Integer.parseInt( SendOTPActivity.phone ),"Trực Tuyến" );
                }else {
                    bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigation1);
                    loadFragment( new NoLive_Fragment() );
                    imgOnOff.setImageDrawable( getResources().getDrawable( ic_baseline_brightness_1_24 ) );
                    txtOnOff.setText( "Ngoại Tuyến" );
                    ModelStatusShipper shipstatus=dao.updatestatusShip( Integer.parseInt( SendOTPActivity.phone ),"Ngoại Tuyến" );
                }
            }
        } );

    }


    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED) {
            //  getLastLocation();
            checkSettingAndStartLocationUpdates();
        } else {
            askLocationPermission();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopLocationUpdate();;
    }

    private  void  checkSettingAndStartLocationUpdates(){
        LocationSettingsRequest request=new LocationSettingsRequest.Builder()
                .addLocationRequest( locationRequest ).build();
        SettingsClient client=LocationServices.getSettingsClient( HomeActivity.this );
        Task<LocationSettingsResponse> locationSettingsResponseTask=client.checkLocationSettings( request );
        locationSettingsResponseTask.addOnSuccessListener( new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                startLocationUpdate();
            }
        } );
        locationSettingsResponseTask.addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure(@NonNull  Exception e) {
                if (e instanceof ResolvableApiException){
                    ResolvableApiException apiException= (ResolvableApiException) e;
                    try {
                        apiException.startResolutionForResult( HomeActivity.this,1001 );
                    } catch (IntentSender.SendIntentException sendIntentException) {
                        sendIntentException.printStackTrace();
                    }
                }
            }
        } );
    }

    @SuppressLint("MissingPermission")
    private  void startLocationUpdate(){
        fusedLocationProviderClient.requestLocationUpdates( locationRequest,locationCallback, Looper.getMainLooper() );
    }
    private  void stopLocationUpdate(){
        fusedLocationProviderClient.removeLocationUpdates( locationCallback );
    }

    private void getLastLocation() {
        @SuppressLint("MissingPermission") Task<Location> locationTask=fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener( new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!=null){
                    Log.d( TAG, "onSuccess: "+location.toString() );
                    Log.d( TAG, "onSuccess: "+location.getLatitude() );
                    Log.d( TAG, "onSuccess: "+location.getLongitude() );
                }
                else {
                    Log.d( TAG, "onSuccess: Location was null" );
                }
            }
        } );
        locationTask.addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure(@NonNull  Exception e) {
                Log.e( TAG, "onFailure: "+e.getLocalizedMessage()  );
            }
        } );

    }

    private void askLocationPermission() {
        if (ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale( this, Manifest.permission.ACCESS_FINE_LOCATION )) {
                Log.d( TAG, "askLocayionPermission: you should show an alert dialog" );
                ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE );
            } else {
                ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE );

            }
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // getLastLocation();
                checkSettingAndStartLocationUpdates();
            } else {

            }
        }
    }





    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigation
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()){
                case R.id.navigation_home:
                    fragment = new OrderFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_account:
                    fragment = new AccoutFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigation1
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()){
                case R.id.navigation_home:
                    fragment = new NoLive_Fragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_account:
                    fragment = new AccoutFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}