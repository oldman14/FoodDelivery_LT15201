package com.example.shipper_lt15201.ModelDetailOrder;

import java.util.ArrayList;

public class ModelListDetailOrder {
    ArrayList<ModelDetailOrder> DetailOrder;

    public ModelListDetailOrder(ArrayList<ModelDetailOrder> detailOrder) {
        DetailOrder = detailOrder;
    }

    public ArrayList<ModelDetailOrder> getDetailOrder() {
        return DetailOrder;
    }

    public void setDetailOrder(ArrayList<ModelDetailOrder> detailOrder) {
        DetailOrder = detailOrder;
    }
}
