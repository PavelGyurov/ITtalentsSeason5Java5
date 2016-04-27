package com.company;

public class Supplier extends Thread{

    private Warehouse warehouse;

    public Supplier(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while(true) {
            warehouse.addToWarehouse();
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

}
