package com.example.fooddelivery_lt152011.MyOrder;

public class ModelStore {
    private int StoreID;
    private String StoreName;
    private String StoreAddress;
    private int StorePhone;
    private double StoreLat;
    private double StoreLong;
    private String Token;
    private String StoreImage;

    public ModelStore(int storeID, String storeName, String storeAddress, int storePhone, double storeLat, double storeLong, String token, String storeImage) {
        StoreID = storeID;
        StoreName = storeName;
        StoreAddress = storeAddress;
        StorePhone = storePhone;
        StoreLat = storeLat;
        StoreLong = storeLong;
        Token = token;
        StoreImage = storeImage;
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

    public String getStoreAddress() {
        return StoreAddress;
    }

    public void setStoreAddress(String storeAddress) {
        StoreAddress = storeAddress;
    }

    public int getStorePhone() {
        return StorePhone;
    }

    public void setStorePhone(int storePhone) {
        StorePhone = storePhone;
    }

    public double getStoreLat() {
        return StoreLat;
    }

    public void setStoreLat(double storeLat) {
        StoreLat = storeLat;
    }

    public double getStoreLong() {
        return StoreLong;
    }

    public void setStoreLong(double storeLong) {
        StoreLong = storeLong;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getStoreImage() {
        return StoreImage;
    }

    public void setStoreImage(String storeImage) {
        StoreImage = storeImage;
    }
}