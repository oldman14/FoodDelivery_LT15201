package com.example.fooddelivery_lt152011.HomeScreen;

import com.example.fooddelivery_lt152011.HTTP_URL;
import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.SystemService;
import com.example.fooddelivery_lt152011.productScreen.Product;

import java.util.ArrayList;

public class CountProductDAO {

    SystemService systemService;

    public CountProductDAO() {
        HttpAdapter adapter = new HttpAdapter();
        adapter.setBaseUrl( HTTP_URL.Final_URL );
        systemService = adapter.create(SystemService.class);
    }

    public ArrayList<Product> listcoupon(){
        ArrayList<Product> countPr=systemService.countProduct().getCounts();
        return countPr;
    }

}
