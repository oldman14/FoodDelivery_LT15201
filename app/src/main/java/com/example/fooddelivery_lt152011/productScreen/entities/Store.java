package com.example.fooddelivery_lt152011.productScreen.entities;

public class Store {
    public int StoreID;
    public String StoreName;
    public String Address;
    public int StorePhone;
    public Double StoreLat;
    public Double StoreLng;
    public String StoreImage;
    public int Token;

    public Store(int storeID, String storeName, String address, int storePhone, Double storeLat, Double storeLng, String storeImage, int token) {
        StoreID = storeID;
        StoreName = storeName;
        Address = address;
        StorePhone = storePhone;
        StoreLat = storeLat;
        StoreLng = storeLng;
        StoreImage = storeImage;
        Token = token;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int storeID) {
        StoreID = storeID;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getStorePhone() {
        return StorePhone;
    }

    public void setStorePhone(int storePhone) {
        StorePhone = storePhone;
    }

    public Double getStoreLat() {
        return StoreLat;
    }

    public void setStoreLat(Double storeLat) {
        StoreLat = storeLat;
    }

    public Double getStoreLng() {
        return StoreLng;
    }

    public void setStoreLng(Double storeLng) {
        StoreLng = storeLng;
    }

    public String getStoreImage() {
        return StoreImage;
    }

    public void setStoreImage(String storeImage) {
        StoreImage = storeImage;
    }

    public int getToken() {
        return Token;
    }

    public void setToken(int token) {
        Token = token;
    }
}
