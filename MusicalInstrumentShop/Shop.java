package com.company;

import java.util.*;

public class Shop {
    public enum Order{ Ascending, Descending }

    private String name;
    private double money;
    private HashMap<Instrument, Integer> soldProducts;
    private TreeMap<Instrument, Integer> products;
    private Supplier supplier;

    public Shop(String name) {
        this.name = name;
        money = 1000;
        products = new TreeMap<Instrument, Integer>();
        soldProducts = new HashMap<Instrument, Integer>();
    }

    //getters

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public HashMap<Instrument, Integer> getSoldProducts() {
        return soldProducts;
    }

    public TreeMap<Instrument, Integer> getProducts() {
        return products;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    //methods

    public void sellInstrument(String name, int quantity){
        Instrument toSell = null;
        for (Instrument i : products.keySet()){
            if (i.getName().equals(name)){
                toSell = i;
                break;
            }
        }
        if (toSell == null){
            System.out.println("No such instrument - " + name);
        } else {
            if (products.get(toSell) >= quantity){
                if (quantity > 1){
                    System.out.println(this.name +  " sold " + quantity + " " + toSell.getName() + "s.");
                }else {
                    System.out.println(this.name +  " sold " + quantity + " " + toSell.getName() + ".");
                }
                products.put(toSell, products.get(toSell) - quantity);
                money += toSell.getPrice()*quantity;
                if (!soldProducts.containsKey(toSell)){
                    soldProducts.put(toSell, quantity);
                }else {
                    soldProducts.put(toSell, soldProducts.get(toSell) + quantity);
                }
            } else {
                System.out.println(this.name + " doesn't have enough " + toSell.getName() + "s.");
                System.out.println("Ordering product...");
                supplier.orderProduct(toSell, quantity - products.get(toSell));     //orders the difference

                products.put(toSell, 0);            //sells all in stock
                money += toSell.getPrice()*quantity;
                if (!soldProducts.containsKey(toSell)){
                    soldProducts.put(toSell, quantity);
                }else {
                    soldProducts.put(toSell, soldProducts.get(toSell) + quantity);
                }
            }
        }
    }

    public void addInstrument(Instrument instrument, int quantity){
        if (!products.containsKey(instrument)){
            products.put(instrument, quantity);
        }else {
            products.put(instrument, products.get(instrument) + quantity);
        }
    }

    public synchronized void restockInstrument(String name, int quantity){
        if (quantity < 0) {
            System.out.println("Cannot stock with negative quantity.");
            throw new IllegalArgumentException();
        }
        Instrument toStock = null;
        for (Instrument i : products.keySet()){
            if (i.getName().equals(name)){
                toStock = i;
                break;
            }
        }
        if (toStock == null){
            System.out.println("No such instrument - " + name);
        } else {
           products.put(toStock, products.get(toStock) + quantity);
        }
    }

    public void showCatalogueByName(){
        System.out.println("Product - price");
        for (Instrument i : products.keySet()){
            System.out.println(i.getName() + " - " + i.getPrice());
        }
    }

    public void showCatalogueByPrice(Order order){
        TreeSet<Instrument> sortedByPrice = new TreeSet<Instrument>(new Comparator<Instrument>() {
            @Override
            public int compare(Instrument o1, Instrument o2) {
                int a = 1;
                int b = -1;
                if (order == Order.Descending){
                    a = -1;
                    b = 1;
                }
                if (o1.getPrice() > o2.getPrice()){
                    return a;
                }else {
                    return b;
                }
            }
        });
        sortedByPrice.addAll(products   .keySet());
        System.out.println("Product - price");
        for (Instrument i : sortedByPrice){
            System.out.println(i.getName() + " - " + i.getPrice());
        }
    }

    public void showCatalogueByCategory(){
        TreeMap<String, TreeSet<Instrument>> categories = new TreeMap<String, TreeSet<Instrument>>();
        for (Instrument i : products.keySet()){
            if (!categories.containsKey(i.getCategory())){
                categories.put(i.getCategory(), new TreeSet<Instrument>());
            }else {
                categories.get(i.getCategory()).add(i);
            }
        }
        for (Map.Entry<String, TreeSet<Instrument>> e : categories.entrySet()){
            System.out.println(e.getKey());
            for (Instrument i: e.getValue()){
                System.out.println(i.getName() + ", price: " + i.getPrice());
            }
        }
    }

    public void showProductsInStock(){
        System.out.println("Product - quantity in stock");
        for (Map.Entry<Instrument, Integer> e : products.entrySet()){
            if (e.getValue() > 0){
                System.out.println(e.getKey().getName() + " - " + e.getValue());
            }
        }
    }

    public void showSoldByQuantity(){
        TreeMap<Integer, Instrument> toPrint = swapSoldKeyValue();
        System.out.println("Product - quantity sold");
        for (Map.Entry<Integer, Instrument> e: toPrint.entrySet()){
            System.out.println(e.getValue().getName() + " - " + e.getKey());
        }
    }

    public double getProfit(){
        double profit = 0;
        for (Map.Entry<Instrument, Integer> e : soldProducts.entrySet()){
            profit += e.getKey().getPrice()*e.getValue();
        }
        return profit;
    }

    public void showMostSoldInstrument(){
        TreeMap<Integer, Instrument> map = swapSoldKeyValue();

        System.out.println("Most sold instrument: + " + map.firstEntry().getValue().getName() + ", units sold: " + map.firstKey());
    }

    public void showLeastSoldInstrument(){
        TreeMap<Integer, Instrument> map = swapSoldKeyValue();
        System.out.println("Least sold instrument: + " + map.lastEntry().getValue().getName() + ", units sold: " + map.lastKey());
    }

    private TreeMap<Integer, Instrument> swapSoldKeyValue(){
        TreeMap<Integer, Instrument> swapped = new TreeMap<Integer, Instrument>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {                //allows same values
                if (o1 > o2){
                    return -1;
                }else {
                    return 1;
                }
            }
        });

        for (Map.Entry<Instrument, Integer> e : soldProducts.entrySet()){
            swapped.put(e.getValue(), e.getKey());
        }
        return swapped;
    }

    public void showCategoryByProfit(){
        TreeMap<String, Integer> categories = new TreeMap<String, Integer>();
        for (Map.Entry<Instrument, Integer> e : soldProducts.entrySet()){
            if (!categories.containsKey(e.getKey().getCategory())){
                categories.put(e.getKey().getCategory(), e.getValue());
            }else {
                categories.put(e.getKey().getCategory(), categories.get(e.getKey().getCategory()) + e.getValue());
            }
        }
        System.out.println("Category - units sold");
        for (Map.Entry<String, Integer> e : categories.entrySet()){
            System.out.println(e.getKey() + " - " + e.getValue());
        }
    }

    public void showCategoryByQuantity(){
        TreeMap<String, Double> categories = new TreeMap<String, Double>();
        for (Map.Entry<Instrument, Integer> e : soldProducts.entrySet()){
            if (!categories.containsKey(e.getKey().getCategory())){
                categories.put(e.getKey().getCategory(), e.getValue()*e.getKey().getPrice());
            }else {
                categories.put(e.getKey().getCategory(), categories.get(e.getKey().getCategory()) + e.getValue()*e.getKey().getPrice());
            }
        }
        System.out.println("Category - profit");
        for (Map.Entry<String, Double> e : categories.entrySet()){
            System.out.println(e.getKey() + " - " + e.getValue());
        }
    }

    public synchronized ArrayList<Instrument> getOutOfStock(){
        ArrayList<Instrument> outOfStock = new ArrayList<Instrument>();
        for (Map.Entry<Instrument, Integer> e : products.entrySet()){
            if (e.getValue() == 0){
                outOfStock.add(e.getKey());
            }
        }
        return outOfStock;
    }
}
