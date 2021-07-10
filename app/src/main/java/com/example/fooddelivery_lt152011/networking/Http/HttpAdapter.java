package com.example.fooddelivery_lt152011.networking.Http;

import android.content.Context;

import java.util.HashMap;

public class HttpAdapter {
    public HttpAdapter( ){
    }


    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private String baseUrl;
    private HashMap<String, String> headers = new HashMap<>();

    public void clearHeaders() {
        headers.clear();
    }

    public void addHeader(String key, String value) {
        if(headers.containsKey(key))
            headers.remove(key);
        headers.put(key, value);
    }
    public void removeHeader(String key) {
        if(headers.containsKey(key))
            headers.remove(key);
    }
    public <T> T create(Class<T> type) {
        try {
            return Factory.create(type, baseUrl,headers);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
