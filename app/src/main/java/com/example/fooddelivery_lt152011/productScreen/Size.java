package com.example.fooddelivery_lt152011.productScreen;

public class Size {
    public int SizeID;
    public String SizeName;
    public int SizePrice;
    public int ProductID;

    public Size(int sizeID, String sizeName, int sizePrice, int productID) {
        SizeID = sizeID;
        SizeName = sizeName;
        SizePrice = sizePrice;
        ProductID = productID;
    }

    public int getSizeID() {
        return SizeID;
    }

    public void setSizeID(int sizeID) {
        SizeID = sizeID;
    }

    public String getSizeName() {
        return SizeName;
    }

    public void setSizeName(String sizeName) {
        SizeName = sizeName;
    }

    public int getSizePrice() {
        return SizePrice;
    }

    public void setSizePrice(int sizePrice) {
        SizePrice = sizePrice;
    }
    public int getProductID() {
        return ProductID;
    }
    public void setProductID(int productID) {
        ProductID = productID;
    }
}
