package com.company;

public class ProductByWeight extends Product {

    private double quantity;

    public ProductByWeight(String name, double price, double quantity) {
        super(name, price);
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            this.quantity = 1;
        }
    }

    public void setQuantity(double quantity) {
       if (quantity >= 0){
           this.quantity = quantity;
       }else {
           System.out.println("Cannot have negative quantity");
       }

    }

    public double getQuantity() {

        return quantity;
    }

    @Override
    public void showInfo() {
        System.out.println(this.getName() + " - " + this.getQuantity() + " quantity. Price: " + this.getPrice());
    }
}
