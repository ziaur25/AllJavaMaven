package com.ziaur.sales;

import lombok.Data;

@Data
public class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
