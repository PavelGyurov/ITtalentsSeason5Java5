package com.company;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Warehouse{

	private static final int MIN_QUANTITY = 5;
	private TreeMap<String, TreeMap<String, Integer>> products;
    private String name;
	
	Warehouse(String name) {
		products = new TreeMap<>();
		products.put("Vegetables", new TreeMap<>());
		products.get("Vegetables").put("Eggplant", 15);
		products.get("Vegetables").put("Potato", 15);
		products.get("Vegetables").put("Cucumber", 15);
		products.put("Fruits", new TreeMap<>());
		products.get("Fruits").put("Banana", 15);
		products.get("Fruits").put("Apple", 15);
		products.get("Fruits").put("Orange", 15);
		products.put("Meats", new TreeMap<>());
		products.get("Meats").put("Pork", 15);
		products.get("Meats").put("Chicken", 15);
		products.get("Meats").put("Beef", 15);
        this.name = name;
	}
	
	
	public synchronized void takeFromWarehouse(String product, Shop shop) {
		if(isDepleted(product)){
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		else{
			take(product, shop);
			notifyAll();
		}
	}	


	public synchronized void addToWarehouse() {
		ArrayList<String> depleted = getDepletedProducts();
		if(depleted.size() == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			for(String product : depleted) {
				fillQuantity(product);
			}
			notifyAll();
		}
	}
	
	public void fillQuantity(String product) {
		int quantity = 0;
		for(Entry<String, TreeMap<String, Integer>> e : products.entrySet()) {
			TreeMap<String, Integer> productsByType = e.getValue();
			if(productsByType.containsKey(product)) {
				productsByType.put(product, productsByType.get(product) + 25);
				quantity = productsByType.get(product);
				break;
			}
		}
		System.out.println(this.realGetName() + " adds 25 " + product + ". Products left = " + quantity);
		
	}
	
	public ArrayList<String> getDepletedProducts() {
		ArrayList<String> depleted = new ArrayList<>();
		for(Entry<String, TreeMap<String, Integer>> e : products.entrySet()) {
			TreeMap<String, Integer> productsByType = e.getValue();
			for(Entry<String, Integer> e1 : productsByType.entrySet()) {
				if(e1.getValue() <= MIN_QUANTITY)
					depleted.add(e1.getKey());
			}
		}
		return depleted;
	}
	
	public boolean isDepleted(String product) {
		for(Entry<String, TreeMap<String, Integer>> e : products.entrySet()) {
			TreeMap<String, Integer> productsByType = e.getValue();
			if(productsByType.containsKey(product)) {
				return productsByType.get(product) <= MIN_QUANTITY;
			}
		}
		return true;
	}

	public void take(String product, Shop shop) {
		int quantity = 0;
		for(Entry<String, TreeMap<String, Integer>> e : products.entrySet()) {
			TreeMap<String, Integer> productsByType = e.getValue();
			if(productsByType.containsKey(product)) {
				productsByType.put(product, productsByType.get(product) - 3);
				quantity = productsByType.get(product);
				break;
			}
		}
		System.out.println(this.realGetName() + " stocks " + shop.realGetName() + " with 3 " + product + "s. Current quantity in " +
                this.realGetName() + " = " + quantity);
	}

    public TreeMap<String, TreeMap<String, Integer>> getProducts() {
        return products;
    }

    public String realGetName() {
        return name;
    }
}
