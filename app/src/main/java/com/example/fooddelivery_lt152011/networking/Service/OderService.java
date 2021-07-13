package com.example.fooddelivery_lt152011.networking.Service;

import com.example.fooddelivery_lt152011.networking.HttpAnotation.Form;
import com.example.fooddelivery_lt152011.networking.HttpAnotation.HttpMethod;
import com.example.fooddelivery_lt152011.networking.HttpAnotation.MethodType;
import com.example.fooddelivery_lt152011.productScreen.ProductReponse;

import java.util.HashMap;

public interface OderService {
    @HttpMethod(method = MethodType.GET,url = "totnghiep_database/api/product_getallsize.php")
    ProductReponse getListProduct();
    @HttpMethod(method = MethodType.POST,url = "totnghiep_database/api/Oder_insert.php")
    boolean insertOder(@Form(name="oder") String oder);
}
