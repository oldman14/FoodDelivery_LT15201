package com.example.fooddelivery_lt152011.productScreen.entities;

public class Favorite {
    private String userID;
    private int productID;

    public Favorite(String userID, int productID) {
        this.userID = userID;
        this.productID = productID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
