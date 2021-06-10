package com.example.fooddelivery_lt152011.SystemService;


import com.example.fooddelivery_lt152011.LoginScreen.ModelStatusUser;
import com.example.fooddelivery_lt152011.LoginScreen.ModelUser;
import com.example.httpconnection.HttpAnotation.Form;
import com.example.httpconnection.HttpAnotation.HttpMethod;
import com.example.httpconnection.HttpAnotation.MethodType;

public interface SystemService {
    @HttpMethod(method = MethodType.POST, url = "Food/api_user/LoginRegisDevice.php")
    ModelStatusUser loginRegisDevice(@Form(name = "phone") String phone, @Form(name = "token") String token);

    @HttpMethod(method = MethodType.POST, url = "Food/api_user/GetUser.php/")
    ModelUser getUser(@Form(name = "phone") String phone);
}