package com.example.fooddelivery_lt152011.networking.Service;

import com.example.fooddelivery_lt152011.networking.HttpAnotation.Form;
import com.example.fooddelivery_lt152011.networking.HttpAnotation.HttpMethod;
import com.example.fooddelivery_lt152011.networking.HttpAnotation.MethodType;
import com.example.fooddelivery_lt152011.productScreen.ProductReponse;

import java.util.HashMap;

public interface OderService {
    @HttpMethod(method = MethodType.GET,url = "FOODDELIVERY/api/product_getallsize.php")
    ProductReponse getListProduct();
    @HttpMethod(method = MethodType.POST,url = "FOODDELIVERY/api/Order_insert.php")
    boolean insertOder(@Form(name="orderfood") String orderfood);
}
