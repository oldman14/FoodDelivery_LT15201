package com.example.fooddelivery_lt152011.productScreen;

import com.example.fooddelivery_lt152011.productScreen.entities.Favorite;

import java.util.List;

public class FavoriteRespone {
    List<Favorite> favorites;

    public FavoriteRespone(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }
}
