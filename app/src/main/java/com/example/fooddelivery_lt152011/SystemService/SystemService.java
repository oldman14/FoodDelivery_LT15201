package com.example.fooddelivery_lt152011.SystemService;


import com.example.fooddelivery_lt152011.LoginScreen.ModelStatusUser;
import com.example.fooddelivery_lt152011.LoginScreen.ModelUser;
import com.example.fooddelivery_lt152011.MyOrder.ModelOrder;
import com.example.fooddelivery_lt152011.MyOrder.ModelShipper;
import com.example.httpconnection.HttpAnotation.Form;
import com.example.httpconnection.HttpAnotation.HttpMethod;
import com.example.httpconnection.HttpAnotation.MethodType;

public interface SystemService {
    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/User_insert.php")
    ModelStatusUser loginRegisDevice(@Form(name = "phone") String phone, @Form(name = "token") String token);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/User_getItem.php/")
    ModelUser getUser(@Form(name = "phone") String phone);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/getUserNames.php/")
    ModelUser getUserNames(@Form( name="UserPhone" ) int UserPhone);

    @HttpMethod( method = MethodType.POST,url = "FOODDELIVERY/api/User_update.php")
    ModelStatusUser updateUser(@Form(name = "UserPhone") int UserPhone, @Form( name="UserName" )String UserName, @Form(name = "UserMail") String UserMail, @Form(name = "UserBirthday") String UserBirthday, @Form(name = "UserImage") String UserImage);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/getShipperViewUsers.php/")
    ModelOrder getItemOrder(@Form( name="UserID" ) int UserID);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/getShip.php/")
    ModelShipper getShip(@Form( name="ShipID" ) int ShipID);
}