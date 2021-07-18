package com.example.fooddelivery_lt152011.HomeScreen;

import java.util.Date;

public class ModelCoupon {
    private int CouponID;
    private String CouponImagge;
    private Date DateStart;
    private Date DateEnd;
    private String CouponNote;
    private String CouponCondition;
    private int CouponPrice;
    private String Status;

    public ModelCoupon(int couponID, String couponImagge, Date dateStart, Date dateEnd, String couponNote, String couponCondition, int couponPrice, String status) {
        CouponID = couponID;
        CouponImagge = couponImagge;
        DateStart = dateStart;
        DateEnd = dateEnd;
        CouponNote = couponNote;
        CouponCondition = couponCondition;
        CouponPrice = couponPrice;
        Status = status;
    }

    public int getCouponID() {
        return CouponID;
    }

    public void setCouponID(int couponID) {
        CouponID = couponID;
    }

    public String getCouponImagge() {
        return CouponImagge;
    }

    public void setCouponImagge(String couponImagge) {
        CouponImagge = couponImagge;
    }

    public Date getDateStart() {
        return DateStart;
    }

    public void setDateStart(Date dateStart) {
        DateStart = dateStart;
    }

    public Date getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        DateEnd = dateEnd;
    }

    public String getCouponNote() {
        return CouponNote;
    }

    public void setCouponNote(String couponNote) {
        CouponNote = couponNote;
    }

    public String getCouponCondition() {
        return CouponCondition;
    }

    public void setCouponCondition(String couponCondition) {
        CouponCondition = couponCondition;
    }

    public int getCouponPrice() {
        return CouponPrice;
    }

    public void setCouponPrice(int couponPrice) {
        CouponPrice = couponPrice;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
