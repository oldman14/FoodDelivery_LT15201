package com.example.fooddelivery_lt152011.productScreen.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery_lt152011.productScreen.ProductReponse;
import com.example.fooddelivery_lt152011.productScreen.repositories.ProductRepository;

public class ProductListViewModel extends ViewModel {
    private ProductRepository productRepository;
    public ProductListViewModel(){
        productRepository = new ProductRepository();
    }
    public LiveData<ProductReponse> getProductReponse(){
        return productRepository.getProductRepository();
    }
}
