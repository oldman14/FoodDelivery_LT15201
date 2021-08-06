package com.example.fooddelivery_lt152011.HomeScreen;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.fooddelivery_lt152011.HTTP_URL;
import com.example.fooddelivery_lt152011.MainActivity;
import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.SystemService;
import com.example.fooddelivery_lt152011.productScreen.FavoriteRespone;
import com.example.fooddelivery_lt152011.productScreen.Product;
import com.example.fooddelivery_lt152011.productScreen.entities.Favorite;

import java.util.ArrayList;
import java.util.List;

public class CountProductDAO {

    SystemService systemService;

    public CountProductDAO() {
        HttpAdapter adapter = new HttpAdapter();
        adapter.setBaseUrl( HTTP_URL.Final_URL );
        systemService = adapter.create(SystemService.class);
    }

    public List<Product> listcoupon(){
        List<Product> countPr=systemService.countProduct().getCounts();
        return countPr;
    }
}
