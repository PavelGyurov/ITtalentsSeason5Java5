package com.company;

public class Store {

    private final String address;
    private String name;
    private double moneyInRegister;
    private Product[] products;
    int freePlacesForProducts;

    public Store(String name, String address, double moneyInRegister, int freePlacesForProducts) {
        if (address != "" && name != ""){
            this.address = address;
            this.name = name;
        } else {
            this.name = "General store";
            this.address = "Middle of nowhere";
        }
        if (moneyInRegister > 0){
            this.moneyInRegister = moneyInRegister;
        } else {
            this.moneyInRegister = 1000;
        }
        if (freePlacesForProducts > 0){
            this.freePlacesForProducts = freePlacesForProducts;
        } else {
            this.freePlacesForProducts = 10;
        }
        products = new Product[freePlacesForProducts];
    }

    public String getName() {
        return name;
    }

    public double getMoneyInRegister() {
        return moneyInRegister;
    }

    public void setMoneyInRegister(double moneyInRegister) {
        if (moneyInRegister > 0){
            this.moneyInRegister = moneyInRegister;
        } else {
            moneyInRegister = 0;
        }
    }

    public void listAllProducts(){
        System.out.println("Products in " + name);
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null){
                products[i].showInfo();
            }
        }
    }

    public void addProduct(Product product){
        if (freePlacesForProducts > 0){
            products[products.length - freePlacesForProducts] = product;
            freePlacesForProducts--;
        } else {
            System.out.println("No room for product");
        }
    }
}
