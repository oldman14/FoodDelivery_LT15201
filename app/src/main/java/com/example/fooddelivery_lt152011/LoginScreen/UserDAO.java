package com.example.fooddelivery_lt152011.LoginScreen;

import android.content.Context;

import com.example.fooddelivery_lt152011.SystemService.SystemService;
import com.example.httpconnection.Http.HttpAdapter;


public class UserDAO {
    Context context;
    SystemService systemService;

    public UserDAO(Context context) {
        this.context = context;
        HttpAdapter adapter = new HttpAdapter(context);
        adapter.setBaseUrl("http://192.168.88.155:81/");
        systemService = adapter.create(SystemService.class);
    }

    public ModelStatusUser loginRegisDevice(String phone, String token){
        ModelStatusUser status = systemService.loginRegisDevice(phone, token);
        return status;
    }

    public ModelUser getUser(String phone){
        ModelUser user = systemService.getUser(phone);
        return user;
    }



}
