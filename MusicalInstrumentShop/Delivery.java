package com.company;

public class Delivery extends Thread{

    private int time;
    private Shop shop;
    private Instrument instrument;
    private int quantity;

    public Delivery(int time, Shop shop, Instrument instrument, int quantity) {
        if (time > 0){
            this.time = time;
        }else {
            this.time = 0;
        }
        if (shop != null){
            this.shop = shop;
        }
        if (instrument != null){
            this.instrument = instrument;
        }
        if (quantity > 0){
            this.quantity = quantity;
        }else {
            this.quantity = 1;
        }
    }

    @Override
    public void run() {
        System.out.println("Shipping of " + instrument.getName() + " started.");
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Delivered " + quantity + " " + instrument.getName() + " to " + shop.getName());
        shop.restockInstrument(instrument.getName(), quantity);
    }

    public int getTime() {
        return time;
    }

    public Shop getShop() {
        return shop;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public int getQuantity() {
        return quantity;
    }
}
