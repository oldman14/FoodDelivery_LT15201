package com.example.fooddelivery_lt152011.productScreen.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery_lt152011.HTTP_URL;
import com.example.fooddelivery_lt152011.LoginScreen.DbHelper;
import com.example.fooddelivery_lt152011.LoginScreen.SendOTPActivity;
import com.example.fooddelivery_lt152011.LoginScreen.VerifyOTPActivity;
import com.example.fooddelivery_lt152011.MainActivity;
import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.FavoriteService;
import com.example.fooddelivery_lt152011.networking.Service.ProductService;
import com.example.fooddelivery_lt152011.networking.Service.TypeProductService;
import com.example.fooddelivery_lt152011.productScreen.FavoriteRespone;
import com.example.fooddelivery_lt152011.productScreen.entities.Favorite;
import com.example.fooddelivery_lt152011.productScreen.entities.Store;
import com.example.fooddelivery_lt152011.productScreen.entities.StoreResponse;

import java.util.ArrayList;
import java.util.List;

public class FavoriteRepo {
    private FavoriteService favoriteService;
    HttpAdapter httpAdapter;
    public MutableLiveData<List<Favorite>> favorite = new MutableLiveData<>();
    public FavoriteRepo() {
        httpAdapter = new HttpAdapter();
        httpAdapter.setBaseUrl( HTTP_URL.Final_URL);
        favoriteService = httpAdapter.create(FavoriteService.class);
    }

    public LiveData<List<Favorite>> getFavorite() {
        try {
            Log.d("TAG", "getFavorite: "+ MainActivity.UserID);
            FavoriteRespone favoriteRespone = favoriteService.getAllFav(MainActivity.UserID);
            List<Favorite> favorites = favoriteRespone.getFavorites();
            Log.d("TAG", "getFavorite: "+favorites);
            favorite.setValue(favorites);
            return favorite;
        } catch (Exception e){
            Log.d("TAG", "getFavorite: "+e);
        }
        return null;
    }


}
