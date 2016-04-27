package com.company;

public class ProductByNumber extends Product{

    private int numberOfItems;

    public ProductByNumber(String name, double price, int numberOfItems) {
        super(name, price);
        if (numberOfItems > 0) {
            this.numberOfItems = numberOfItems;
        } else {
            this.numberOfItems = 1;
        }
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    @Override
    public void showInfo() {
        System.out.println(this.getName() + " - " + this.getNumberOfItems() + " items. Price: " + this.getPrice());
    }
}
