package com.example.fooddelivery_lt152011.productScreen.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery_lt152011.productScreen.CartItem;
import com.example.fooddelivery_lt152011.productScreen.ListTypeProduct;
import com.example.fooddelivery_lt152011.productScreen.Product;
import com.example.fooddelivery_lt152011.productScreen.Size;
import com.example.fooddelivery_lt152011.productScreen.TypeProduct;
import com.example.fooddelivery_lt152011.productScreen.entities.Favorite;
import com.example.fooddelivery_lt152011.productScreen.repositories.CartRepo;
import com.example.fooddelivery_lt152011.productScreen.repositories.FavoriteRepo;
import com.example.fooddelivery_lt152011.productScreen.repositories.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private MutableLiveData<List<Product>> product = new MutableLiveData<>();
    private MutableLiveData<List<TypeProduct>> typeProduct  = new MutableLiveData<>();
    public int quantity = 0;
    public MutableLiveData<Integer> totalQuantity = new MutableLiveData<>();
    public MutableLiveData<Integer> quantityItem = new MutableLiveData<>();
    public MutableLiveData<Boolean> favourite = new MutableLiveData<>();
    private MutableLiveData<Product> mutableProduct = new MutableLiveData<>();
    private MutableLiveData<Boolean> isConnect = new MutableLiveData<>();
    private MutableLiveData<Size> size = new MutableLiveData<>();
    private MutableLiveData<Integer> priceProduct = new MutableLiveData<>();
    private MutableLiveData<Boolean> isOrder = new MutableLiveData<>();
    private MutableLiveData<CartItem> cartItemMutable = new MutableLiveData<>();
    private MutableLiveData<Boolean> isEditing = new MutableLiveData<>();
    CartRepo cartItemRepo = new CartRepo();
    ProductRepository productRepository = new ProductRepository();


    public MutableLiveData<CartItem> getCartItemMutable() {
        return cartItemMutable;
    }

    public void setCartItemMutable(CartItem cartItem) {
        cartItemMutable.setValue(cartItem);
    }

    public LiveData<List<Product>> getFavorite() {
        return productRepository.getFavorite();
    }

    public LiveData<Boolean> getIsEditing() {
        if (isEditing==null){
            isEditing.setValue(false);
        }
        return isEditing;
    }
    public void setIsEditing(boolean aboolean) {
        isEditing.setValue(aboolean);
    }
    public LiveData<Boolean> getIsConnect() {
        if (isConnect==null){
            isConnect = new MutableLiveData<>();
        }
        return isConnect;
    }
    public void setIsConnect(boolean aboolean) {
        isConnect.setValue(aboolean);
    }

    public void setIsOrder(boolean aboolean) {
        isOrder.setValue(aboolean);
    }

    public LiveData<Boolean> getIsOrder() {
        if (isOrder==null){
            isOrder = new MutableLiveData<>();
        }
        return isOrder;
    }


    public ProductViewModel(@NonNull Application application) {
        super(application);
        quantityItem.setValue(0);
        favourite.setValue(false);
    }

    public MutableLiveData<Integer> getPriceProduct() {
        if (priceProduct==null){
            priceProduct = new MutableLiveData<>();
        }
        return priceProduct;
    }

    public void setPriceProduct(Integer price) {
        priceProduct.setValue(price);
    }

    public LiveData<Size> getSize(){
        if (size == null){
            size = new MutableLiveData<>();
        }
        return size;
    }
    public void setSize(Size s) {
        size.setValue(s);
    }
    public LiveData<Boolean> getFavoite(){
        if (favourite==null){
            favourite = new MutableLiveData<>();
//            favourite.setValue(false);
        }
        return favourite;
    }
    public void setFavourite(boolean aboolean){
        favourite.setValue(aboolean);
    }
    public void changFavourite(){
        if (favourite.getValue()==true){
            favourite.setValue(false);
        } else favourite.setValue(true);
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


    public LiveData<List<ListTypeProduct>> getTypeProduct() {
        return productRepository.getTypeProducts();
    }

    public void setTypeProduct(MutableLiveData<List<TypeProduct>> typeProduct) {
        this.typeProduct = typeProduct;
    }
    //cart

    public LiveData<List<CartItem>> getCart() {
        return cartItemRepo.getCart();
    }

    public boolean addItemToCart(Product product, int quantity ,Size size, int amount) {
        return cartItemRepo.addItemToCart(product, quantity, size, amount);
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

    public void minusQuantity(boolean isEdit){
        if (isEdit==true){
            if (quantityItem.getValue()>0){
                quantityItem.setValue(quantityItem.getValue()-1);
            }
        } else if (quantityItem.getValue()>1){
            quantityItem.setValue(quantityItem.getValue()-1);
        }
    }
    public void plusQuantity(){
        quantityItem.setValue(quantityItem.getValue()+1);
    }


}
