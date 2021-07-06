package com.example.shipper_lt15201.ModelOrder;

public class ModelOrder {
    private String OrderID;
    private int UserID;
    private int StoreID;
    private int ShipID;
    private String Address;
    private double OrderLat;
    private double OrderLong;
    private String OrderDate;
    private int CouponID;
    private double TotalMoney;
    private String Note;
    private String Status;
    private int UserPhone;

    public ModelOrder(String orderID, int userID, int storeID, int shipID, String address, double orderLat, double orderLong, String orderDate, int couponID, double totalMoney, String note, String status, int userPhone) {
        OrderID = orderID;
        UserID = userID;
        StoreID = storeID;
        ShipID = shipID;
        Address = address;
        OrderLat = orderLat;
        OrderLong = orderLong;
        OrderDate = orderDate;
        CouponID = couponID;
        TotalMoney = totalMoney;
        Note = note;
        Status = status;
        UserPhone = userPhone;
    }

    public ModelOrder() {

    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int storeID) {
        StoreID = storeID;
    }

    public int getShipID() {
        return ShipID;
    }

    public void setShipID(int shipID) {
        ShipID = shipID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public double getOrderLat() {
        return OrderLat;
    }

    public void setOrderLat(double orderLat) {
        OrderLat = orderLat;
    }

    public double getOrderLong() {
        return OrderLong;
    }

    public void setOrderLong(double orderLong) {
        OrderLong = orderLong;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public int getCouponID() {
        return CouponID;
    }

    public void setCouponID(int couponID) {
        CouponID = couponID;
    }

    public double getTotalMoney() {
        return TotalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        TotalMoney = totalMoney;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(int userPhone) {
        UserPhone = userPhone;
    }
}
