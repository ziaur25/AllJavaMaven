package com.ziaur.sales;

public enum Store {

    LETTERKENNY(1,"LETTERKENNY"), DERRY(2,"DERRY");

    int id;
    String name;

    Store(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
