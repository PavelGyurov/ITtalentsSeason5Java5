package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Supplier extends Thread{

    private HashMap<Instrument, Integer> products;
    private Shop shop;
    private boolean running;

    public Supplier(Shop shop) {
        this.shop = shop;
        products = new HashMap<Instrument, Integer>();
        running = true;
    }

    public void addInstrument(Instrument instrument){
        Random rng = new Random();
        if (!products.containsKey(instrument)){
            products.put(instrument, rng.nextInt(2001) + 1000);
        }
    }

    public void orderProduct(Instrument instrument, int quantity){
        if (products.containsKey(instrument)){
            Delivery delivery = new Delivery(products.get(instrument), shop, instrument, quantity);
            delivery.start();
        }else {
            System.out.println("This supplier doesn't offer that instrument.");
        }
    }

    @Override
    public void run() {
      while (running){
          try {
              Thread.sleep(10000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          ArrayList<Instrument> toDeliver = shop.getOutOfStock();
          for (Instrument i : toDeliver){
              Delivery delivery = new Delivery(products.get(i), shop, i, 1);
              delivery.start();
          }
      }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
