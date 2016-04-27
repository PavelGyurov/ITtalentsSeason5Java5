package com.company;

public abstract class Product {

    private String name;
    private double price;

    public Product(String name, double price) {
        if (name != ""){
            this.name = name;
        } else {
            this.name = "Product";
        }
        if (price > 0) {
            this.price = price;
        } else {
            this.price = 1;
        }
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public abstract void showInfo();
}
