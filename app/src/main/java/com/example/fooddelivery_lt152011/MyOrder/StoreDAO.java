package com.example.fooddelivery_lt152011.MyOrder;

import com.example.fooddelivery_lt152011.HTTP_URL;
import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.SystemService;

public class StoreDAO {

    SystemService systemService;
    public StoreDAO() {
        com.example.fooddelivery_lt152011.networking.Http.HttpAdapter adapter = new HttpAdapter();
        adapter.setBaseUrl( HTTP_URL.Final_URL );
        systemService = adapter.create(SystemService.class);
    }


    public  ModelStore getIFStore(int storeID){
        ModelStore getifstore=systemService.getIFStore( storeID );
        return getifstore;
    }
}
