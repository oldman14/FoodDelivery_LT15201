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
        productType.add(new TypeProduct(1, "Đá xoay"));
        productType.add(new TypeProduct(2, "Nước ép"));
        productType.add(new TypeProduct(3, "Bánh xu cà na"));
        return productType;
    }
    public List<Product> getAllProduct(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(0, "Cà phê đen", 20000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè ",0));
        productList.add(new Product(1, "Cà phê sữa đá", 32000,"https://product.hstatic.net/1000075078/product/cafe-sua-da_9073dfe2143d428a91a370e79df77e49_large.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè ",0));
        productList.add(new Product(2, "Bạc xỉu", 25000,"https://product.hstatic.net/1000075078/product/bac-siu_13856adaa2354499aa61251b8b1e9fd6_large.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè ",0));
        productList.add(new Product(3, "Đá xoay socola", 35000,"https://product.hstatic.net/1000075078/product/cafe-da-say_3de4ce716b534b1183736c2ce0cdbc25_large.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè ",1));
        productList.add(new Product(4, "Đá xoay cà phê", 30000,"https://product.hstatic.net/1000075078/product/cookie-da-xay_43c2bc99f313405aa253b803dcd59030_large.jpg ", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè",1));
        productList.add(new Product(5, "Đá xoay ca cao", 50000,"https://product.hstatic.net/1000075078/product/dao-viet-quat-da-xay_7e464338009c432985f8e3cdba6acb38_large.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè",1));
        productList.add(new Product(4, "Nước ép cà phê", 30000,"https://product.hstatic.net/1000075078/product/cam-pbt-da-xay_06ca55fce8e84389ab9d707f4bd753a7_large.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè",2));
        productList.add(new Product(5, "Nước ép ca cao", 50000,"https://product.hstatic.net/1000075078/product/dao-viet-quat-da-xay_7e464338009c432985f8e3cdba6acb38_large.jpg", "Một tách cà phê thơm ngào ngạt",2));
        return productList;
    }
}
