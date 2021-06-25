package com.example.fooddelivery_lt152011.productScreen;

import java.util.List;

public class TypeProduct {
    public int TypeID;
    public String TypeName;
    public String TypeNote;
    public List<Product> products;

    public TypeProduct(int typeID, String typeName) {
        TypeID = typeID;
        TypeName = typeName;
    }

    public TypeProduct(int typeID, String typeName, List<Product> products) {
        TypeID = typeID;
        TypeName = typeName;
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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
