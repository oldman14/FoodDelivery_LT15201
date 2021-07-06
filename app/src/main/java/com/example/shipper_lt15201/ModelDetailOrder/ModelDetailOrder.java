package com.example.shipper_lt15201.ModelDetailOrder;

public class ModelDetailOrder {
    private int DetailID;
    private String OrderID;
    private int ProductID;
    private int Amount;
    private int CartMoney;
    private String ProductName;
    private int ProductPrice;
    private String ProductImage;
    private String ProductNote;
    private int TypeID;
    private String TypeName;
    private String TypeNote;

    public ModelDetailOrder(int detailID, String orderID, int productID, int amount, int cartMoney, String productName, int productPrice, String productImage, String productNote, int typeID, String typeName, String typeNote) {
        DetailID = detailID;
        OrderID = orderID;
        ProductID = productID;
        Amount = amount;
        CartMoney = cartMoney;
        ProductName = productName;
        ProductPrice = productPrice;
        ProductImage = productImage;
        ProductNote = productNote;
        TypeID = typeID;
        TypeName = typeName;
        TypeNote = typeNote;
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

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getCartMoney() {
        return CartMoney;
    }

    public void setCartMoney(int cartMoney) {
        CartMoney = cartMoney;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(int productPrice) {
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
}
