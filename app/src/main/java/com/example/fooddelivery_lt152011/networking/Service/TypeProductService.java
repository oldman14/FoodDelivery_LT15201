package com.example.fooddelivery_lt152011.networking.Service;

import com.example.fooddelivery_lt152011.networking.HttpAnotation.HttpMethod;
import com.example.fooddelivery_lt152011.networking.HttpAnotation.MethodType;
import com.example.fooddelivery_lt152011.productScreen.TypeResponse;

public interface TypeProductService {
    @HttpMethod(method = MethodType.GET,url = "FoodDelivery/api/TypeProduct_getAll.php")
    TypeResponse getListTypeProduct();
}
