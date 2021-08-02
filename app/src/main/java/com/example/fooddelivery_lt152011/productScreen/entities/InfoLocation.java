package com.example.fooddelivery_lt152011.productScreen.entities;

import android.location.Location;

public class InfoLocation {
    public Location location;
    public  String address;

    public InfoLocation(Location location, String address) {
        this.location = location;
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
