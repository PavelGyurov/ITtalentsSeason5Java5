package com.company;

import java.util.Random;

public class Customer extends Thread{

    private Shop shop;
    private String[] products = {"Banana", "Orange", "Apple", "Potato", "Eggplant", "Cucumber", "Pork", "Beef", "Chicken"};
    private Random rand = new Random();
    private String name;

    public Customer(String name, Shop shop) {
        this.shop = shop;
        this.name = name;
    }

    @Override
    public void run() {
        while(true) {
            int productIndex = rand.nextInt(9);
            int quantity = rand.nextInt(4) + 1;
            shop.take(products[productIndex], quantity);
            System.out.println(this.realGetName() + " buys " + quantity + " " + products[productIndex] + "s from " + shop.realGetName());
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
            }
        }
    }

    public String realGetName() {
        return name;
    }
}
