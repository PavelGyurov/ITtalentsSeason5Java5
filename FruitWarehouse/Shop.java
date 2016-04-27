package com.company;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Shop extends Warehouse implements Runnable{

    private Warehouse warehouse;
    private static final int MIN_QUANTITY = 3;
    private String name;

    public Shop(String name, Warehouse warehouse) {
        super(name);
        this.warehouse = warehouse;
        this.name = name;
    }

    @Override
    public void run() {
        while(true) {
            ArrayList<String> depletedProducts = this.getDepletedProducts();
            for (String product : depletedProducts){
                warehouse.takeFromWarehouse(product, this);
            }
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }


    public void take(String product, int quantity) {
        int quantityLeft = 0;
        for(Map.Entry<String, TreeMap<String, Integer>> e : this.getProducts().entrySet()) {
            TreeMap<String, Integer> productsByType = e.getValue();
            if(productsByType.containsKey(product)) {
                if (productsByType.get(product) > quantity) {
                    productsByType.put(product, productsByType.get(product) - quantity);
                }
                quantityLeft = productsByType.get(product);
                break;
            }
        }
        System.out.println(this.realGetName() + " takes " + quantity + " of " + product + ". Current quantity left = " + quantityLeft);
    }

    public String realGetName() {
        return name;
    }
}
