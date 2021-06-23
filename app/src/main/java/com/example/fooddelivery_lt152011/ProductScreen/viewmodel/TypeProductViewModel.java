package com.example.fooddelivery_lt152011.ProductScreen.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery_lt152011.ProductScreen.TypeResponse;
import com.example.fooddelivery_lt152011.ProductScreen.repositories.ProductRepository;

public class TypeProductViewModel extends ViewModel {
    private ProductRepository productRepository;
    public TypeProductViewModel(){
        productRepository = new ProductRepository();
    }
    public LiveData<TypeResponse> getTypeProductReponse(){
        return productRepository.getTypeProductResponsitory();
    }
}
