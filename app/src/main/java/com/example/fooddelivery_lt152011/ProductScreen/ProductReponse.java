package com.example.fooddelivery_lt152011.ProductScreen;

import java.util.List;

public class ProductReponse {
    private List<Product> products;

    public ProductReponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProduct() {
        return products;
    }

    public void setProduct(List<Product> products) {
        this.products = products;
    }
}
