package com.example.shipper_lt15201.SystemService;
import com.example.httpconnection.HttpAnotation.Form;
import com.example.httpconnection.HttpAnotation.HttpMethod;
import com.example.httpconnection.HttpAnotation.MethodType;
import com.example.shipper_lt15201.LoginScreen.ModelShipper;
import com.example.shipper_lt15201.LoginScreen.ModelStatusShipper;
import com.example.shipper_lt15201.ModelDetailOrder.ModelListDetailOrder;
import com.example.shipper_lt15201.ModelOrder.ModelListOrder;
import com.example.shipper_lt15201.ModelOrder.ModelStatusOrder;
import com.example.shipper_lt15201.ModelUser.ModelUser;

public interface SystemService {
    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/Shipper_insert.php")
    ModelStatusShipper loginRegisDevice(@Form(name = "phone") int phone, @Form(name = "token") String token);
    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/getShipperName.php/")
    ModelShipper getShipperName(@Form( name="ShipPhone" ) int ShipPhone);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/getOrder.php/")
    ModelListOrder getItemOrder(@Form( name="ShipID" ) int ShipID);
    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/updateStatusOrder.php/")
    ModelStatusOrder updateStatus(@Form( name="OrderID" ) String OrderID,@Form( name = "Status") String status);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/getUserToOrder.php/")
    ModelUser getUserNames(@Form( name="UserID" ) int UserID);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/DetailOder_getByOrderID.php/")
    ModelListDetailOrder getDetail(@Form( name="OrderID" ) String OrderID);

    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/Update_StatusShipper.php/")
    ModelStatusShipper updatestatusShip(@Form( name="ShipPhone" ) int ShipPhone,@Form( name = "Status") String status);



    @HttpMethod(method = MethodType.POST, url = "FOODDELIVERY/api/UpdateLocationShip.php/")
    ModelStatusShipper updateLocationShip(@Form( name="ShipPhone" ) int ShipPhone,@Form( name = "ShipLat") Double shipLat,@Form( name = "ShipLong") Double ShipLong);

}