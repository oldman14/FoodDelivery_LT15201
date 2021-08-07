package com.example.fooddelivery_lt152011.productScreen.viewmodel;

import android.app.Application;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery_lt152011.productScreen.entities.InfoLocation;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.jetbrains.annotations.NotNull;

public class LocationViewModel extends AndroidViewModel {
    public MutableLiveData<String> addressUser = new MutableLiveData<>();
    public MutableLiveData<InfoLocation> location = new MutableLiveData<>();
    public MutableLiveData<String> streetName = new MutableLiveData<>();

    public MutableLiveData<InfoLocation> getLocation() {
        return location;
    }

    public void setLocation(InfoLocation lo) {
        location.setValue(lo);
    }
    public void setStreetName(String name){
        streetName.setValue(name);
    }
    public MutableLiveData<String> getStreetName(){
        return streetName;
    }
    public MutableLiveData<String> getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(String address) {
        addressUser.setValue(address);
    }

    public LocationViewModel(@NonNull @NotNull Application application) {
        super(application);
    }
}
