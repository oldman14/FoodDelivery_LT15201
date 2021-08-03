package com.example.fooddelivery_lt152011.networking.Service;


import com.example.fooddelivery_lt152011.networking.HttpAnotation.HttpMethod;
import com.example.fooddelivery_lt152011.networking.HttpAnotation.MethodType;
import com.example.fooddelivery_lt152011.productScreen.ProductReponse;
import com.example.fooddelivery_lt152011.productScreen.SizeResponse;

import java.util.List;

public interface ProductService {
        @HttpMethod(method = MethodType.GET,url = "FoodDelivery/api/Product_getAll.php")
        ProductReponse getListProduct();
        @HttpMethod(method = MethodType.GET,url = "FoodDelivery/api/product_getallsize.php")
        List<SizeResponse> getAllSize();
}
