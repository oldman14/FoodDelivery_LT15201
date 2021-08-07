package com.example.fooddelivery_lt152011;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;//có rôf
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.fooddelivery_lt152011.AccountScreen.AccountFragment;
import com.example.fooddelivery_lt152011.HomeScreen.HomeFragment;
import com.example.fooddelivery_lt152011.LoginScreen.DbHelper;
import com.example.fooddelivery_lt152011.LoginScreen.VerifyOTPActivity;
import com.example.fooddelivery_lt152011.MyOrder.InforOderFragment;
import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.StoreService;
import com.example.fooddelivery_lt152011.productScreen.MyConnect;
import com.example.fooddelivery_lt152011.productScreen.ProductFragment;
import com.example.fooddelivery_lt152011.productScreen.ProductReponse;
import com.example.fooddelivery_lt152011.productScreen.entities.InfoLocation;
import com.example.fooddelivery_lt152011.productScreen.entities.Store;
import com.example.fooddelivery_lt152011.productScreen.entities.StoreResponse;
import com.example.fooddelivery_lt152011.productScreen.repositories.FavoriteRepo;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.LocationViewModel;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductViewModel;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.StoreViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public static final String BroadcastStrngforAction = "checkinternet";
    private IntentFilter intentFilter;
    public static BottomNavigationView navigationView;
    private ProductViewModel productViewModel;
    FusedLocationProviderClient fusedLocationProviderClient;
    Location myLocation = null;
    Geocoder geocoder;
    List<Address> addresses;
    public  static Toolbar toolbar;
    private final static int LOCATION_REQUEST_CODE = 23;
    boolean locationPermission = false;
    private TextView tv_address_toolbar;
    StoreViewModel storeViewModel;
    double lat, lng;
    InfoLocation infoLocation;
    public  static RelativeLayout toolbar_logo, toolbar_address;
    DbHelper dbHelper;
    public static int UserID;
    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
    public BroadcastReceiver MyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BroadcastStrngforAction)) {
                if (intent.getStringExtra("online_status").equals("true")) {
                    productViewModel.setIsConnect(true);
                } else {
                    productViewModel.setIsConnect(false);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar_logo = findViewById(R.id.toolbar1);
        toolbar_address =findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigation);
        navigationView.setSelectedItemId(R.id.navigation_product);
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        loadFragment(new ProductFragment());
        intentFilter = new IntentFilter();
        intentFilter.addAction(BroadcastStrngforAction);
        Intent serviceIntent = new Intent(this, MyConnect.class);
        startService(serviceIntent);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        requestPermision();
        tv_address_toolbar = findViewById(R.id.tv_address_toolbar);
        dbHelper = new DbHelper(MainActivity.this);
        UserID = dbHelper.getUser().getUserID();
        //create storeModel
        if (locationPermission=true){
            getMyLocation();
        }
        storeViewModel = new ViewModelProvider(this).get(StoreViewModel.class);
        if (isNetworkConnected(getApplicationContext())) {
            productViewModel.setIsConnect(true);

        } else {
            productViewModel.setIsConnect(false);
        }
//        FavoriteRepo favoriteRepo = new FavoriteRepo();
//        favoriteRepo.getFavorite();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        registerReceiver(MyReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(MyReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(MyReceiver, intentFilter);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigation
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    toolbar_address.setVisibility(View.GONE);
                    toolbar_logo.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_product:
                    fragment = new ProductFragment();
                    loadFragment(fragment);
                    toolbar_address.setVisibility(View.VISIBLE);
                    toolbar_logo.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_account:
                    fragment = new AccountFragment();
                    loadFragment(fragment);
                    toolbar_address.setVisibility(View.GONE);
                    toolbar_logo.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commit();
    }

    private void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            Log.d("TAG", "getMyLocation: "+"fail");
            return;
        }
        geocoder = new Geocoder(this, Locale.getDefault());
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                myLocation = task.getResult();
//                lat = myLocation.getLatitude();
//                lng = myLocation.getLongitude();
                final double[] distance = {10000};
                storeViewModel.getListStore().observe(MainActivity.this, new Observer<List<Store>>() {
                    @Override
                    public void onChanged(List<Store> stores) {
                        for (int i = 0; i < stores.size(); i++) {
                            double storeLat = stores.get(i).StoreLat;
                            double storeLng = stores.get(i).StoreLng;
                            float[] results=new float[1];
                            Location locationStore = new Location("");
                            locationStore.setLatitude(storeLat);
                            locationStore.setLongitude(storeLng);
                            if (myLocation!=null){
                                double km = myLocation.distanceTo(locationStore);
                                if (km/1000< distance[0]){
                                    distance[0] = km/1000;
                                    storeViewModel.setStore(stores.get(i));
                                }
                            }
                        }
                    }
                });
                if (task.isSuccessful()) {
                    if (myLocation != null) {
                        LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                        try {
                            addresses = geocoder.getFromLocation(myLocation.getLatitude(), myLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        String city = addresses.get(0).getLocality();
                        String state = addresses.get(0).getAdminArea();
                        String country = addresses.get(0).getCountryName();
                        String postalCode = addresses.get(0).getPostalCode();
                        String nameStreet = addresses.get(0).getThoroughfare();
                        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
                        Log.d("TAG", "onComplete: "+"state"+state+"nameStreet"+nameStreet+"knơname"+knownName);
                        tv_address_toolbar.setText(address);
                        String streetName = knownName + " "+nameStreet;
                        infoLocation = new InfoLocation(myLocation, address);
                        LocationViewModel locationViewModel = new ViewModelProvider(MainActivity.this).get(LocationViewModel.class);
                        locationViewModel.setLocation(infoLocation);
                        locationViewModel.setStreetName(streetName);
                    }
                }
            }
        });
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //if permission granted.
                    locationPermission=true;
                    getMyLocation();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    private void requestPermision()
    {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_REQUEST_CODE);
        }
        else{
            locationPermission=true;
        }
    }
    private void getStoreID(){
    }

    }

