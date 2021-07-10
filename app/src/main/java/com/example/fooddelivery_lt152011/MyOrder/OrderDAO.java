package com.example.fooddelivery_lt152011.MyOrder;

import android.content.Context;

import com.example.fooddelivery_lt152011.SystemService.SystemService;
import com.example.httpconnection.Http.HttpAdapter;

import java.util.ArrayList;

public class OrderDAO {

    Context context;
    SystemService systemService;

    public OrderDAO(Context context) {
        this.context = context;
        HttpAdapter adapter = new HttpAdapter(context);
        adapter.setBaseUrl("http://192.168.1.9/");
        systemService = adapter.create(SystemService.class);
    }

    public ModelOrder getItemOrder(int userID){
        ModelOrder itemorder=systemService.getItemOrder( userID );
        return itemorder;
    }


}
