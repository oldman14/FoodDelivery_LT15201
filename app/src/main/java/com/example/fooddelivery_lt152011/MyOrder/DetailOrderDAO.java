package com.example.fooddelivery_lt152011.MyOrder;

import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.SystemService;

import java.util.ArrayList;

public class DetailOrderDAO {

    SystemService systemService;

    public DetailOrderDAO( ) {
        HttpAdapter adapter = new HttpAdapter();
        adapter.setBaseUrl("http://192.168.1.9/");
        systemService = adapter.create(SystemService.class);
    }
   public ArrayList<ModelDetailOrder> detailOrders(String orderID){
        ArrayList<ModelDetailOrder> itemdetail=systemService.getDetail( orderID ).getDetailOrder();
        return itemdetail;
   }
}
