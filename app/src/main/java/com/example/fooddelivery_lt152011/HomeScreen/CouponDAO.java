package com.example.fooddelivery_lt152011.HomeScreen;

import com.example.fooddelivery_lt152011.MyOrder.ModelOrder;
import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.SystemService;

import java.util.ArrayList;

public class CouponDAO {
    SystemService systemService;

    public CouponDAO() {
        HttpAdapter adapter = new HttpAdapter();
        adapter.setBaseUrl("http://192.168.1.9/");
        systemService = adapter.create(SystemService.class);
    }

    public ArrayList<ModelCoupon> listcoupon(String status){
     ArrayList<ModelCoupon> itemcoupon=systemService.coupon( status ).getCoupons();
        return itemcoupon;
    }
}
