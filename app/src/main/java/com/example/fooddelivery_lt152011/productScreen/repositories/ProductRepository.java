    package com.example.fooddelivery_lt152011.productScreen.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.ProductService;
import com.example.fooddelivery_lt152011.networking.Service.TypeProductService;
import com.example.fooddelivery_lt152011.productScreen.ListTypeProduct;
import com.example.fooddelivery_lt152011.productScreen.Product;
import com.example.fooddelivery_lt152011.productScreen.ProductReponse;
import com.example.fooddelivery_lt152011.productScreen.SizeResponse;
import com.example.fooddelivery_lt152011.productScreen.TypeProduct;
import com.example.fooddelivery_lt152011.productScreen.TypeResponse;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private ProductService productService;
    private TypeProductService typeProductService;
    HttpAdapter httpAdapter;
    public MutableLiveData<ProductReponse> product = new MutableLiveData<>();
    public MutableLiveData<TypeResponse> typeProduct = new MutableLiveData<>();
    public MutableLiveData<List<ListTypeProduct>> listTypeProduct;
    private MutableLiveData<List<TypeProduct>> typeProductRes;

    public ProductRepository() {
        httpAdapter = new HttpAdapter();
        httpAdapter.setBaseUrl("https://192.168.171.3/");
        productService = httpAdapter.create(ProductService.class);
        typeProductService = httpAdapter.create(TypeProductService.class);
    }
    public LiveData<List<TypeProduct>> getProducts() {
        if (typeProductRes == null) {
            typeProductRes = new MutableLiveData<>();
            getListProduct();
        }
        return typeProductRes;
    }
    public LiveData<List<ListTypeProduct>> getTypeProducts(){
        if (listTypeProduct==null){
            listTypeProduct = new MutableLiveData<>();
            getListTypeProduct();
        }
        return listTypeProduct;
    }
    public void getListTypeProduct(){
        try{
            TypeResponse typeResponses = typeProductService.getListTypeProduct();
            List<ListTypeProduct> typeProductList = typeResponses.getTypeProduct();
            listTypeProduct.setValue(typeProductList);
        } catch (Exception e){
            Log.d("TAG", "getListTypeProduct: "+e.getMessage());
        }


    }
    public void getListProduct(){
        try {
            List<TypeProduct> productType = new ArrayList<>();
            ProductReponse productReponses = productService.getListProduct();
            TypeResponse typeResponses = typeProductService.getListTypeProduct();
            List<Product> productList = productReponses.getProduct();
            List<ListTypeProduct> typeProductList = typeResponses.getTypeProduct();
            for (int i = 0; i < typeResponses.getTypeProduct().size(); i++) {
                List<Product> productListTypeProduct = new ArrayList<>();
                for (int j = 0; j < productReponses.getProduct().size(); j++) {
                    if (typeProductList.get(i).getTypeID()==productList.get(j).TypeID){
                        productListTypeProduct.add(productList.get(j));
                    }
                }
                if (productListTypeProduct.size()>0){
                    productType.add(new TypeProduct(typeProductList.get(i).getTypeID(), typeProductList.get(i).getTypeName(), productListTypeProduct));
                }
                typeProductRes.setValue(productType);
            }
        }catch (Exception e){
            Log.d("TAG", "getListProduct: "+e);
        }
    }
    public void getListSize(){

    }
}
