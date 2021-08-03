package com.example.fooddelivery_lt152011.productScreen.entities;

public class Favorite {
    private int FavoriteID;
    private int UserID;
    private int ProductID;

    public Favorite(int favoriteID, int userID, int productID) {
        FavoriteID = favoriteID;
        UserID = userID;
        ProductID = productID;
    }

    public int getFavoriteID() {
        return FavoriteID;
    }

    public void setFavoriteID(int favoriteID) {
        FavoriteID = favoriteID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }
}
