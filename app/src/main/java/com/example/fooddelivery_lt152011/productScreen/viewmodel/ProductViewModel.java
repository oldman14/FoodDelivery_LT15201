package com.example.fooddelivery_lt152011.productScreen.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery_lt152011.productScreen.Product;
import com.example.fooddelivery_lt152011.productScreen.TypeProduct;
import com.example.fooddelivery_lt152011.productScreen.repositories.TypeProRes;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private MutableLiveData<List<Product>> product = new MutableLiveData<>();
    private MutableLiveData<List<TypeProduct>> typeProduct  = new MutableLiveData<>();

    private MutableLiveData<Product> mutableProduct = new MutableLiveData<>();

    TypeProRes typeProRes = new TypeProRes();

    public ProductViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<TypeProduct>> getProducts() {
        return typeProRes.getProducts();
    }
    public LiveData<Product> getProduct(){
        return mutableProduct;
    }
    public void setProduct(Product product) {
        mutableProduct.setValue(product);
    }

    public MutableLiveData<List<TypeProduct>> getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(MutableLiveData<List<TypeProduct>> typeProduct) {
        this.typeProduct = typeProduct;
    }
}
