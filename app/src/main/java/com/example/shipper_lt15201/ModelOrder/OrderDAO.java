package com.example.shipper_lt15201.ModelOrder;

import android.content.Context;

import com.example.httpconnection.Http.HttpAdapter;
import com.example.shipper_lt15201.SystemService.SystemService;

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

//    public ModelOrder getItemOrder(int shipID){
//        ModelOrder itemorder=systemService.getItemOrder( shipID );
//        return itemorder;
//    }

    public ArrayList<ModelOrder> getItemOrder(int shipID){
        ArrayList<ModelOrder> order = systemService.getItemOrder( shipID ).getOrders();
        return order;
    }


    private ModelStatusOrder updateStatus(String orderID,String status){
        ModelStatusOrder update=systemService.updateStatus( orderID,status );
        return update;
    }

}
