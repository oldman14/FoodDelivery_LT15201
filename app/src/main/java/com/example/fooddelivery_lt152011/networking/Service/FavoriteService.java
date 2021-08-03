package com.example.fooddelivery_lt152011.networking.Service;

import com.example.fooddelivery_lt152011.networking.HttpAnotation.Form;
import com.example.fooddelivery_lt152011.networking.HttpAnotation.HttpMethod;
import com.example.fooddelivery_lt152011.networking.HttpAnotation.MethodType;
import com.example.fooddelivery_lt152011.productScreen.FavoriteRespone;

import java.util.List;

public interface FavoriteService {
    @HttpMethod(method = MethodType.POST,url = "FoodDelivery/api/Favorite_getall.php")
    FavoriteRespone getAllFav(@Form(name = "UserID") int UserID);
    @HttpMethod(method = MethodType.POST,url = "FoodDelivery/api/Favorite_insert.php")
    boolean insertFav(@Form(name = "UserID") int UserID, @Form( name="ProductID" )int ProductID);
    @HttpMethod(method = MethodType.POST,url = "FoodDelivery/api/Favorite_delete.php")
    boolean deleteFav(@Form(name = "UserID") int UserID, @Form( name="ProductID" )int ProductID);
}
