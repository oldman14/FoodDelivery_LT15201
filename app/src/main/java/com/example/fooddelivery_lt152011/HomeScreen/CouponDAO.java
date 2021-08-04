package com.example.fooddelivery_lt152011.HomeScreen;

import com.example.fooddelivery_lt152011.HTTP_URL;
import com.example.fooddelivery_lt152011.MyOrder.ModelOrder;
import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.SystemService;

import java.util.ArrayList;

public class CouponDAO {
    SystemService systemService;

    public CouponDAO() {
        HttpAdapter adapter = new HttpAdapter();
        adapter.setBaseUrl( HTTP_URL.Final_URL );
        systemService = adapter.create(SystemService.class);
    }

    public ArrayList<ModelCoupon> listcoupon( ){
     ArrayList<ModelCoupon> itemcoupon=systemService.coupon().getCoupon();
        return itemcoupon;
    }
}
