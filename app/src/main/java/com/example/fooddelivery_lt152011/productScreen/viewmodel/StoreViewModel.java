package com.example.fooddelivery_lt152011.productScreen.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery_lt152011.productScreen.TypeProduct;
import com.example.fooddelivery_lt152011.productScreen.entities.Store;
import com.example.fooddelivery_lt152011.productScreen.repositories.StoreRepo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StoreViewModel extends AndroidViewModel {

    StoreRepo storeRepo =new StoreRepo();
    MutableLiveData<Integer> storeID = new MutableLiveData<>();
    public MutableLiveData<List<Store>> listStore  = new MutableLiveData<>();
    public MutableLiveData<Store> store = new MutableLiveData<>();


    public MutableLiveData<Store> getStore() {
        return store;
    }

    public void setStore(Store s) {
        store.setValue(s);
    }

    public MutableLiveData<Integer> getStoreID() {
        return storeID;
    }

    public void setStoreID(int id) {
        storeID.setValue(id);
    }

    public StoreViewModel(@NonNull @NotNull Application application) {
        super(application);
        storeID.setValue(0);
    }
    public LiveData<List<Store>> getListStore(){
        Log.d("TAG", "getListStore: "+storeRepo.getStoreResponse().getValue());
        return  storeRepo.getStoreResponse();
    }

}
