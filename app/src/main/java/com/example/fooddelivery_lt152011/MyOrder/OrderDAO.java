package com.example.fooddelivery_lt152011.MyOrder;

import android.content.Context;

import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.HttpAnotation.HttpMethod;
import com.example.fooddelivery_lt152011.networking.Service.SystemService;

public class OrderDAO {
    SystemService systemService;

    public OrderDAO() {
        HttpAdapter adapter = new HttpAdapter();
        adapter.setBaseUrl("http://192.168.1.9/");
        systemService = adapter.create(SystemService.class);
    }

    public ModelOrder getItemOrder(int userID){
        ModelOrder itemorder=systemService.getItemOrder( userID );
        return itemorder;
    }


}
