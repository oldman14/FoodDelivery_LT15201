package com.example.fooddelivery_lt152011.productScreen.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery_lt152011.HTTP_URL;
import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.StoreService;
import com.example.fooddelivery_lt152011.productScreen.entities.Store;
import com.example.fooddelivery_lt152011.productScreen.entities.StoreResponse;

import java.util.List;

public class StoreRepo {
    HttpAdapter httpAdapter;
    StoreService storeService;

    MutableLiveData<List<Store>> listStore;
    public StoreRepo() {
        httpAdapter = new HttpAdapter();
        httpAdapter.setBaseUrl( HTTP_URL.Final_URL );
        storeService = httpAdapter.create(StoreService.class);
    }
    public LiveData<List<Store>> getStoreResponse() {
        if (listStore==null){
            listStore = new MutableLiveData<>();
            getStore();
        } else{
            Log.d("TAG", "getStoreResponse: "+listStore.getValue());
        }
        return listStore;
    }

    private void getStore() {
        try {
          StoreResponse storeResponse = storeService.getListStore();
          List<Store> stores= storeResponse.getStores();
          Log.d("TAG", "getStore: "+storeResponse.getStores().get(0).getStoreName());
          listStore.setValue(stores);
        } catch (Exception e){
            Log.d("TAG", "getStore: "+e);
        }
    }

    public void setStoreResponse(MutableLiveData<List<Store>> listStore) {
        this.listStore = listStore;
    }


}
