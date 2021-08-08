    package com.example.fooddelivery_lt152011.productScreen.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery_lt152011.productScreen.CartItem;
import com.example.fooddelivery_lt152011.productScreen.Product;
import com.example.fooddelivery_lt152011.productScreen.Size;


import java.util.ArrayList;
import java.util.List;

public class  CartRepo {

    private MutableLiveData<List<CartItem>> mutableCart = new MutableLiveData<>();
    private MutableLiveData<Double> mutableTotalPrice = new MutableLiveData<>();
    private MutableLiveData<Integer> mutableTotalQantity = new MutableLiveData<>();
    private MutableLiveData<Integer> mutableQuantity = new MutableLiveData<>();
    public LiveData<List<CartItem>> getCart() {
        if (mutableCart.getValue() == null) {
            initCart();
        }
        return mutableCart;
    }

    public void initCart() {
        mutableCart.setValue(new ArrayList<CartItem>());
        calculateCartTotal();
    }
    public boolean addItemToCart(Product product, int quantity, Size size, int amount) {
        if (mutableCart.getValue() == null) {
            initCart();
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        for (CartItem cartItem: cartItemList) {
            if (cartItem.getProduct().getProductID()==(product.getProductID())&&cartItem.size==size) {
                int index = cartItemList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItemList.set(index, cartItem);
                mutableCart.setValue(cartItemList);
                calculateCartTotal();
                return true;
            }
        }
        CartItem cartItem = new CartItem(product, quantity, size, amount);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
        return true;
    }

    public void deleteCartOrder(){
        mutableCart.setValue(null);
    }
    public void removeItemFromCart(CartItem cartItem) {
        if (mutableCart.getValue() == null) {
            return;
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        cartItemList.remove(cartItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
    }

    public  void changeQuantity(CartItem cartItem, int quantity) {
        if (mutableCart.getValue() == null) return;
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        CartItem updatedItem = new CartItem(cartItem.getProduct(), quantity,cartItem.size, cartItem.amount);
        cartItemList.set(cartItemList.indexOf(cartItem), updatedItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
    }

    private void calculateCartTotal() {
        if (mutableCart.getValue() == null) return;
        double total = 0.0;
        int quantity = 0;
        List<CartItem> cartItemList = mutableCart.getValue();
        for (CartItem cartItem: cartItemList) {
            total += cartItem.getProduct().getProductPrice() * cartItem.getQuantity();
            quantity += cartItem.getQuantity();
        }
        mutableTotalPrice.setValue(total);
        mutableTotalQantity.setValue(quantity);
    }
    public LiveData<Integer> getCartQuantity(){
        if (mutableTotalQantity.getValue() == null) {
            mutableTotalQantity.setValue(0);
        }
        return mutableTotalQantity;
    }
    public LiveData<Double> getTotalPrice() {
        if (mutableTotalPrice.getValue() == null) {
            mutableTotalPrice.setValue(0.0);
        }
        return mutableTotalPrice;
    }

}
