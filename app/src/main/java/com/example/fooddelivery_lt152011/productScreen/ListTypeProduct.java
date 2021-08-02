package com.example.fooddelivery_lt152011.productScreen;

public class ListTypeProduct {
    public int TypeID;
    public String TypeName;
    public String TypeNote;

    public ListTypeProduct(int typeID, String typeName, String typeNote) {
        TypeID = typeID;
        TypeName = typeName;
        TypeNote = typeNote;
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
