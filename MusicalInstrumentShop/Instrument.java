package com.company;

public class Instrument implements Comparable<Instrument>{

    private String name;
    private String category;
    private double price;

    public Instrument(String name, String category, double price) {
        if (name != null && name != ""){
            this.name = name;
        }else {
            this.name = "Instrument";
        }
        if (category != null && category != ""){
            this.category = category;
        }else {
            this.category = "Undefined";
        }
        if (price > 0){
            this.price = price;
        }else {
            this.price = 100;
        }
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Instrument o) {
        return this.getName().compareTo(o.getName());
    }
}
