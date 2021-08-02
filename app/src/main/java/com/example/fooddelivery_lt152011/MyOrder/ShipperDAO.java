package com.example.fooddelivery_lt152011.MyOrder;

import android.content.Context;

import com.example.fooddelivery_lt152011.HTTP_URL;
import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.SystemService;


public class ShipperDAO {
    SystemService systemService;

    public ShipperDAO() {
        com.example.fooddelivery_lt152011.networking.Http.HttpAdapter adapter = new HttpAdapter();
        adapter.setBaseUrl( HTTP_URL.Final_URL);
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
