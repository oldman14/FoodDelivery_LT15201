package com.example.fooddelivery_lt152011.productScreen.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery_lt152011.productScreen.CartItem;
import com.example.fooddelivery_lt152011.productScreen.Product;
import com.example.fooddelivery_lt152011.productScreen.TypeProduct;
import com.example.fooddelivery_lt152011.productScreen.repositories.CartRepo;
import com.example.fooddelivery_lt152011.productScreen.repositories.ProductRepository;
import com.example.fooddelivery_lt152011.productScreen.repositories.TypeProRes;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private MutableLiveData<List<Product>> product = new MutableLiveData<>();
    private MutableLiveData<List<TypeProduct>> typeProduct  = new MutableLiveData<>();
    public int quantity = 0;
    public MutableLiveData<Integer> totalQuantity = new MutableLiveData<>();
    public MutableLiveData<Integer> quantityItem = new MutableLiveData<>();

    private MutableLiveData<Product> mutableProduct = new MutableLiveData<>();

    TypeProRes typeProRes = new TypeProRes();
    CartRepo cartItemRepo = new CartRepo();

    ProductRepository productRepository = new ProductRepository();
    public ProductViewModel(@NonNull Application application) {
        super(application);
        quantityItem.setValue(0);
    }

    public LiveData<Integer> getQuantityItem(){
        if (quantityItem==null){
            quantityItem = new MutableLiveData<>();
        }
        return quantityItem;
    }
    public void setQuantityItem(int quantity){
        quantityItem.setValue(quantity);
    }

    public LiveData<List<TypeProduct>> getProducts() {
        return productRepository.getProducts();
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
    //cart

    public LiveData<List<CartItem>> getCart() {
        return cartItemRepo.getCart();
    }

    public boolean addItemToCart(Product product) {
        Log.d("TAG", "addItemToCart: ");
        return cartItemRepo.addItemToCart(product);
    }

    public void removeItemFromCart(CartItem cartItem) {
        cartItemRepo.removeItemFromCart(cartItem);
    }

    public void changeQuantity(CartItem cartItem, int quantity) {
        cartItemRepo.changeQuantity(cartItem, quantity);
    }
    public LiveData<Double> getTotalPrice() {
        return cartItemRepo.getTotalPrice();
    }
    public LiveData<Integer> getCartQuantity(){
        return cartItemRepo.getCartQuantity();
    }
    public void resetCart() {
        cartItemRepo.initCart();
    }

    public void minusQuantity(){
        quantityItem.setValue(quantityItem.getValue()-1);
    }
    public void plusQuantity(){
        quantityItem.setValue(quantityItem.getValue()+1);
    }

}
