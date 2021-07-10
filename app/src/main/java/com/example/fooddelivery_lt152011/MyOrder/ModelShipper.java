package com.example.fooddelivery_lt152011.MyOrder;

public class ModelShipper {
  public int ShipID;
  private int StoreID;
  public String ShipName;
  public String ShipImage;
  public int ShipPhone;
  public  String ShipNumberCar;
  public  double ShipLat;
    public  double ShipLong;
    public String Status;
    public String Token;

    public ModelShipper() {
    }

    public ModelShipper(int shipID, int storeID, String shipName, String shipImage, int shipPhone, String shipNumberCar, double shipLat, double shipLong, String status, String token) {
        ShipID = shipID;
        StoreID = storeID;
        ShipName = shipName;
        ShipImage = shipImage;
        ShipPhone = shipPhone;
        ShipNumberCar = shipNumberCar;
        ShipLat = shipLat;
        ShipLong = shipLong;
        Status = status;
        Token = token;
    }

    public int getShipID() {
        return ShipID;
    }

    public void setShipID(int shipID) {
        ShipID = shipID;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int storeID) {
        StoreID = storeID;
    }

    public String getShipName() {
        return ShipName;
    }

    public void setShipName(String shipName) {
        ShipName = shipName;
    }

    public String getShipImage() {
        return ShipImage;
    }

    public void setShipImage(String shipImage) {
        ShipImage = shipImage;
    }

    public int getShipPhone() {
        return ShipPhone;
    }

    public void setShipPhone(int shipPhone) {
        ShipPhone = shipPhone;
    }

    public String getShipNumberCar() {
        return ShipNumberCar;
    }

    public void setShipNumberCar(String shipNumberCar) {
        ShipNumberCar = shipNumberCar;
    }

    public double getShipLat() {
        return ShipLat;
    }

    public void setShipLat(double shipLat) {
        ShipLat = shipLat;
    }

    public double getShipLong() {
        return ShipLong;
    }

    public void setShipLong(double shipLong) {
        ShipLong = shipLong;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
