package com.example.fooddelivery_lt152011.MyOrder;

import android.content.Context;

import com.example.fooddelivery_lt152011.HTTP_URL;
import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.SystemService;

public class OrderDAO {
    SystemService systemService;

    public OrderDAO() {
        HttpAdapter adapter = new HttpAdapter();
        adapter.setBaseUrl( HTTP_URL.Final_URL );
        systemService = adapter.create(SystemService.class);
    }

    public ModelOrder getItemOrder(int userID){
        ModelOrder itemorder=systemService.getItemOrder( userID );
        return itemorder;
    }


}
