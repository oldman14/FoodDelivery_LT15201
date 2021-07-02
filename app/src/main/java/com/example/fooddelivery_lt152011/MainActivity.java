package com.example.fooddelivery_lt152011;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fooddelivery_lt152011.AccountScreen.AccountFragment;
import com.example.fooddelivery_lt152011.HomeScreen.HomeFragment;
import com.example.fooddelivery_lt152011.databinding.FragmentProductBinding;
import com.example.fooddelivery_lt152011.productScreen.MyConnect;
import com.example.fooddelivery_lt152011.productScreen.ProductFragment;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {
   public static final String BroadcastStrngforAction="checkinternet";
    private IntentFilter intentFilter;
    private ProductViewModel productViewModel;
    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo!= null && networkInfo.isConnectedOrConnecting())
        {
            return true;
        } else {
            return false;
        }
    }
    public BroadcastReceiver MyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BroadcastStrngforAction)){
                if (intent.getStringExtra("online_status").equals("true")){
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
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigation);
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        loadFragment(new HomeFragment());
        intentFilter = new IntentFilter();
        intentFilter.addAction(BroadcastStrngforAction);
        Intent serviceIntent = new Intent(this, MyConnect.class);
        startService(serviceIntent);
        if (isNetworkConnected(getApplicationContext())){
            productViewModel.setIsConnect(true);

        } else
        {
            productViewModel.setIsConnect(false);
        }
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
            switch (item.getItemId()){
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_product:
                    fragment = new ProductFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_account:
                    fragment = new AccountFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment (Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

