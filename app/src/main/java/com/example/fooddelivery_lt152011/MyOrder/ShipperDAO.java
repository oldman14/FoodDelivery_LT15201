package com.example.fooddelivery_lt152011.MyOrder;

import android.content.Context;

import com.example.fooddelivery_lt152011.SystemService.SystemService;
import com.example.httpconnection.Http.HttpAdapter;


public class ShipperDAO {
    Context context;
    SystemService systemService;

    public ShipperDAO(Context context) {
        this.context = context;
        HttpAdapter adapter = new HttpAdapter(context);
        adapter.setBaseUrl("http://192.168.1.9/");
        systemService = adapter.create(SystemService.class);
    }

//    public  ModelShipper getShipperName(int phone){
//        ModelShipper nameimg=systemService.getShipperName( phone );
//        return nameimg;
//    }


    public  ModelShipper getShips(int shipID){
        ModelShipper getship=systemService.getShip( shipID );
        return getship;
    }
}
