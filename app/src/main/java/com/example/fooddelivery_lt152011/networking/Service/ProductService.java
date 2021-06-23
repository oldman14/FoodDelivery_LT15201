package com.example.fooddelivery_lt152011.networking.Service;


import com.example.fooddelivery_lt152011.networking.HttpAnotation.HttpMethod;
import com.example.fooddelivery_lt152011.networking.HttpAnotation.MethodType;
import com.example.fooddelivery_lt152011.ProductScreen.ProductReponse;

public interface ProductService {
        @HttpMethod(method = MethodType.GET,url = "totnghiep_database/api/product_getall.php")
        ProductReponse getListProduct();


//        @HttpMethod(method = MethodType.POST,url = "android_networking/api/load_product_brand.php")
//        ProductList getProductForBrand(@Form(name="brand_id") String brand_id);
//        @HttpMethod(method = MethodType.GET,url = "android_networking/api/load_brand.php")
//        BrandList getListBrand();
//        @HttpMethod(method = MethodType.POST,url = "android_networking/api/add_product.php")
//        ProductList getPostProduct(@Form(name="name") String name,@Form(name="price") String price, @Form(name="image") String image,@Form(name="brand_id") String brand_id);
//        @HttpMethod(method = MethodType.POST,url = "android_networking/api/update_product.php")
//        ProductList getUpdateProduct(@Form(name="name") String name,@Form(name="price") String price, @Form(name="image") String image,@Form(name="pid") String pid);
//        @HttpMethod(method = MethodType.POST,url = "android_networking/api/delete_product.php")
//        ProductList deleteProduct(@Form(name="pid") String pid);

}
