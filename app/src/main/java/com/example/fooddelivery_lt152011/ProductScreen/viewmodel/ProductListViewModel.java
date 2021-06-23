package com.example.fooddelivery_lt152011.ProductScreen.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery_lt152011.ProductScreen.ProductReponse;
import com.example.fooddelivery_lt152011.ProductScreen.repositories.ProductRepository;

public class ProductListViewModel extends ViewModel {
    private ProductRepository productRepository;
    public ProductListViewModel(){
        productRepository = new ProductRepository();
    }
    public LiveData<ProductReponse> getProductReponse(){
        return productRepository.getProductRepository();
    }
}
