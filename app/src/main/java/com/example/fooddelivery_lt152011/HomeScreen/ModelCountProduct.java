package com.example.fooddelivery_lt152011.HomeScreen;

import com.example.fooddelivery_lt152011.productScreen.Product;

import java.util.ArrayList;
import java.util.List;

public class ModelCountProduct {
    List<Product> Counts;

    public ModelCountProduct(List<Product> counts) {
        Counts = counts;
    }

    public List<Product> getCounts() {
        return Counts;
    }

    public void setCounts(List<Product> counts) {
        Counts = counts;
    }
}
