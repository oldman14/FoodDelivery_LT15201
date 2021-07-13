package com.example.fooddelivery_lt152011.networking.Service;


import com.example.fooddelivery_lt152011.networking.HttpAnotation.HttpMethod;
import com.example.fooddelivery_lt152011.networking.HttpAnotation.MethodType;
import com.example.fooddelivery_lt152011.productScreen.ProductReponse;

import java.util.List;

public interface ProductService {
        @HttpMethod(method = MethodType.GET,url = "totnghiep_database/api/product_getallsize.php")
        ProductReponse getListProduct();
//        @HttpMethod(method = MethodType.GET,url = "totnghiep_database/api/product_getallsize.php")
//        List<SizeResponse> getAllSize();

}
