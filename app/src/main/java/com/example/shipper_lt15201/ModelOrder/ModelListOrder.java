package com.example.shipper_lt15201.ModelOrder;

import java.util.ArrayList;

public class ModelListOrder {
    private ArrayList<ModelOrder> orders;

    public ArrayList<ModelOrder> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<ModelOrder> orders) {
        this.orders = orders;
    }

    public ModelListOrder() {
    }

    public ModelListOrder(ArrayList<ModelOrder> orders) {
        this.orders = orders;
    }
}
