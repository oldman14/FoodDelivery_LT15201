package com.example.fooddelivery_lt152011.productScreen.entities;

import java.util.List;

public class StoreResponse {
    public List<Store> stores;

    public StoreResponse(List<Store> stores) {
        this.stores = stores;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}
