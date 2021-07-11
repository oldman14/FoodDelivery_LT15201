package com.example.fooddelivery_lt152011.productScreen;

import java.util.List;

public class TypeResponse {
    public List<ListTypeProduct> TypeProduct;

    public TypeResponse(List<ListTypeProduct> typeProduct) {
        TypeProduct = typeProduct;
    }

    public List<ListTypeProduct> getTypeProduct() {
        return TypeProduct;
    }

    public void setTypeProduct(List<ListTypeProduct> typeProduct) {
        TypeProduct = typeProduct;
    }
}
