package com.example.shipper_lt15201.LoginScreen;

import android.content.Context;
import com.example.httpconnection.Http.HttpAdapter;
import com.example.shipper_lt15201.SystemService.SystemService;


public class ShipperDAO {
    Context context;
    SystemService systemService;

    public ShipperDAO(Context context) {
        this.context = context;
        HttpAdapter adapter = new HttpAdapter(context);
        adapter.setBaseUrl("http://192.168.1.9/");
        systemService = adapter.create(SystemService.class);
    }
    public ModelStatusShipper loginRegisDevice(int phone, String token){
        ModelStatusShipper status = systemService.loginRegisDevice(phone, token);
        return status;
    }

    public  ModelShipper getShipperName(int phone){
        ModelShipper nameimg=systemService.getShipperName( phone );
        return nameimg;
    }


    public ModelStatusShipper updatestatusShip(int phone,String status){
        ModelStatusShipper shipstatus=systemService.updatestatusShip( phone,status );
        return  shipstatus;
    }
    public ModelStatusShipper updateLocationShip(int phone,double shipLat,double shipLong){
        ModelStatusShipper locationship=systemService.updateLocationShip( phone,shipLat ,shipLong);
        return  locationship;
    }
}
