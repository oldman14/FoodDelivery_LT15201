package com.example.fooddelivery_lt152011.productScreen.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery_lt152011.productScreen.TypeResponse;
import com.example.fooddelivery_lt152011.productScreen.repositories.ProductRepository;

public class TypeProductViewModel extends ViewModel {
    private ProductRepository productRepository;
    public TypeProductViewModel(){
        productRepository = new ProductRepository();
    }
    public LiveData<TypeResponse> getTypeProductReponse(){
        return productRepository.getTypeProductResponsitory();
    }
}
