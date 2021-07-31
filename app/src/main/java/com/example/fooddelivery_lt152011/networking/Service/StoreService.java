package com.example.fooddelivery_lt152011.networking.Service;

import com.example.fooddelivery_lt152011.networking.HttpAnotation.HttpMethod;
import com.example.fooddelivery_lt152011.networking.HttpAnotation.MethodType;
import com.example.fooddelivery_lt152011.productScreen.entities.StoreResponse;

public interface StoreService {
    @HttpMethod(method = MethodType.GET,url = "FoodDelivery/api/Store_getAll.php")
    StoreResponse getListStore();
}
