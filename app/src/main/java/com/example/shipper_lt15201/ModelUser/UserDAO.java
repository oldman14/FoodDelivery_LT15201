package com.example.shipper_lt15201.ModelUser;

import android.content.Context;

import com.example.httpconnection.Http.HttpAdapter;
import com.example.shipper_lt15201.SystemService.SystemService;

public class UserDAO {

    Context context;
    SystemService systemService;

    public UserDAO(Context context) {
        this.context = context;
        HttpAdapter adapter = new HttpAdapter(context);
        adapter.setBaseUrl("http://192.168.1.9/");
        systemService = adapter.create(SystemService.class);
    }
    public  ModelUser getUserNames(int userID){
        ModelUser ifuser=systemService.getUserNames( userID );
        return ifuser;
    }

}
