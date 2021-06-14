package com.example.fooddelivery_lt152011.productScreen.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery_lt152011.productScreen.Product;
import com.example.fooddelivery_lt152011.productScreen.TypeProduct;

import java.util.ArrayList;
import java.util.List;

public class TypeProRes {
    private MutableLiveData<List<TypeProduct>> typeProductRes;

    public LiveData<List<TypeProduct>> getProducts() {
        if (typeProductRes == null) {
            typeProductRes = new MutableLiveData<>();
            getAllTypePro();
        }
        return typeProductRes;
    }

    private void getAllTypePro() {
        List<TypeProduct> productType = new ArrayList<>();
            List<TypeProduct> listType = getAllType();
            List<Product> productList = getAllProduct();
            typeProductRes  = new MutableLiveData<>();
            for (int i = 0; i < listType.size(); i++) {
                List<Product> productList1 = new ArrayList<>();
                for (int j = 0; j < productList.size(); j++) {
                    if (listType.get(i).TypeID==productList.get(j).TypeID){
                        productList1.add(productList.get(j));
                    }
                }
                if (productList1.size()>0){
                    productType.add(new TypeProduct(listType.get(i).getTypeID() ,listType.get(i).getTypeName(), productList1));
                }
                typeProductRes.setValue(productType);
            }
    }

    private List<TypeProduct> getAllType(){
        List<TypeProduct> productType = new ArrayList<>();
        productType.add(new TypeProduct(0, "Cà Phê"));
        productType.add(new TypeProduct(0, "Cà phê"));
        productType.add(new TypeProduct(1, "Đá xoay vòng tròn"));
        productType.add(new TypeProduct(2, "Nước ép hoa đậu biếc"));
        productType.add(new TypeProduct(3, "Bánh xu cà na"));
        return productType;
    }
    public List<Product> getAllProduct(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(0, "Cà phê đen", 20000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè ",0));
        productList.add(new Product(1, "Cà phê sữa đá", 25000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè ",0));
        productList.add(new Product(2, "Bạc xỉu", 20000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè ",0));
        productList.add(new Product(3, "Đá xoay socola", 35000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè ",1));
        productList.add(new Product(4, "Đá xoay cà phê", 30000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè",1));
        productList.add(new Product(5, "Đá xoay ca cao", 50000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè",1));
        productList.add(new Product(4, "Nước ép cà phê", 30000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè",2));
        productList.add(new Product(5, "Nước ép ca cao", 50000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt",2));
        return productList;
    }
}
