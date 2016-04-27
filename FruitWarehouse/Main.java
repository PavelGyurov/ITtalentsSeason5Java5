package com.company;


public class Main {
	
	static Warehouse warehouse;
	
	public static void main(String[] args) {
		
		warehouse = new Warehouse("Warehouse");
		new Supplier(warehouse).start();
		Shop shop1 = new Shop("Shop 1", warehouse);
        shop1.start();
		Shop shop2 = new Shop("Shop 2", warehouse);
        shop2.start();
		Shop shop3 = new Shop("Shop 3", warehouse);
        shop3.start();

        new Customer("Customer 1" ,shop1).start();
        new Customer("Customer 2" ,shop1).start();
        new Customer("Customer 3" ,shop1).start();
        new Customer("Customer 4" ,shop2).start();
        new Customer("Customer 5" ,shop2).start();
        new Customer("Customer 6" ,shop2).start();
        new Customer("Customer 7" ,shop3).start();
        new Customer("Customer 8" ,shop3).start();
        new Customer("Customer 9" ,shop3).start();
	}
}


