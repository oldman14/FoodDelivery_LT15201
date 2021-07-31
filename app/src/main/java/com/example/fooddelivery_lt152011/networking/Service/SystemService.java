package com.example.fooddelivery_lt152011.networking.Service;


import com.example.fooddelivery_lt152011.HomeScreen.ModelListCoupon;
import com.example.fooddelivery_lt152011.LoginScreen.ModelStatusUser;
import com.example.fooddelivery_lt152011.LoginScreen.ModelUser;
import com.example.fooddelivery_lt152011.MyOrder.ModelListDetailOrder;
import com.example.fooddelivery_lt152011.MyOrder.ModelOrder;
import com.example.fooddelivery_lt152011.MyOrder.ModelShipper;
import com.example.fooddelivery_lt152011.MyOrder.ModelStore;
import com.example.fooddelivery_lt152011.networking.HttpAnotation.Form;
import com.example.fooddelivery_lt152011.networking.HttpAnotation.HttpMethod;
import com.example.fooddelivery_lt152011.networking.HttpAnotation.MethodType;

public interface SystemService {
    @HttpMethod(method = MethodType.POST, url = "FoodDelivery/api/User_insert.php")
    ModelStatusUser loginRegisDevice(@Form(name = "phone") int phone, @Form(name = "token") String token);

    @HttpMethod(method = MethodType.POST, url = "FoodDelivery/api/User_getItem.php/")
    ModelUser getUser(@Form(name = "phone") String phone);

    @HttpMethod(method = MethodType.POST, url = "FoodDelivery/api/getUserNames.php/")
    ModelUser getUserNames(@Form( name="UserPhone" ) int UserPhone);

    @HttpMethod( method = MethodType.POST,url = "FOODDELIVERY/api/User_update.php")
    ModelStatusUser updateUser(@Form(name = "UserPhone") int UserPhone, @Form( name="UserName" )String UserName, @Form(name = "UserMail") String UserMail, @Form(name = "UserBirthday") String UserBirthday, @Form(name = "UserImage") String UserImage);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/getShipperViewUsers.php/")
    ModelOrder getItemOrder(@Form( name="UserID" ) int UserID);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/getShip.php/")
    ModelShipper getShip(@Form( name="ShipID" ) int ShipID);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/getIFStore.php/")
    ModelStore getIFStore(@Form( name="StoreID" ) int StoreID);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/DetailOder_getByOrderID.php/")
    ModelListDetailOrder getDetail(@Form( name="OrderID" ) String OrderID);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/get_Coupon.php/")
    ModelListCoupon coupon(@Form( name="Status" ) String status);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/Order_getNewID.php/")
    int getNewID();
}