package com.example.fooddelivery_lt152011.MyOrder;

public class ModelDetailOrder {
    private int DetailID;
    private String OrderID;
    private int ProductID;
    private int Quantity;
    private int SizeID;
    private int Amount;
    private String ProductName;
    private double ProductPrice;
    private String ProductImage;
    private String ProductNote;
    private int TypeID;
    private String TypeName;
    private String TypeNote;
    private String SizeName;
    private int SizePrice;

    public ModelDetailOrder(int detailID, String orderID, int productID, int quantity, int sizeID, int amount, String productName, double productPrice, String productImage, String productNote, int typeID, String typeName, String typeNote, String sizeName, int sizePrice) {
        DetailID = detailID;
        OrderID = orderID;
        ProductID = productID;
        Quantity = quantity;
        SizeID = sizeID;
        Amount = amount;
        ProductName = productName;
        ProductPrice = productPrice;
        ProductImage = productImage;
        ProductNote = productNote;
        TypeID = typeID;
        TypeName = typeName;
        TypeNote = typeNote;
        SizeName = sizeName;
        SizePrice = sizePrice;
    }

    public int getDetailID() {
        return DetailID;
    }

    public void setDetailID(int detailID) {
        DetailID = detailID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getSizeID() {
        return SizeID;
    }

    public void setSizeID(int sizeID) {
        SizeID = sizeID;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(double productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductNote() {
        return ProductNote;
    }

    public void setProductNote(String productNote) {
        ProductNote = productNote;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getTypeNote() {
        return TypeNote;
    }

    public void setTypeNote(String typeNote) {
        TypeNote = typeNote;
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
}
