package com.company;

public class Main {

    public static void main(String[] args) {

        Shop rf = new Shop("House of the rising force");
        rf.addInstrument(new Instrument("Piano", "Keyboard", 3000), 5);
        rf.addInstrument(new Instrument("Organ", "Keyboard", 7000), 1);
        rf.addInstrument(new Instrument("Accordion", "Keyboard", 600), 10);
        rf.addInstrument(new Instrument("Drums", "Percussion", 2000), 2);
        rf.addInstrument(new Instrument("Tambourine", "Percussion", 60), 15);
        rf.addInstrument(new Instrument("Congas", "Percussion", 400), 4);
        rf.sellInstrument("Organ", 1);
        rf.sellInstrument("Piano", 2);
        rf.sellInstrument("Congas", 4);

        rf.showCatalogueByName();
        System.out.println();
        rf.showCatalogueByPrice(Shop.Order.Ascending);
        System.out.println();
        rf.showCatalogueByPrice(Shop.Order.Descending);
        System.out.println();
        rf.showCatalogueByCategory();
        System.out.println();
        rf.showProductsInStock();
        rf.showSoldByQuantity();
        System.out.println();
        System.out.println(rf.getProfit());
        System.out.println();
        rf.showMostSoldInstrument();
        rf.showLeastSoldInstrument();
        System.out.println();
        rf.showCategoryByProfit();
        System.out.println();
        rf.showCategoryByQuantity();

        Supplier sup = new Supplier(rf);
        for (Instrument i : rf.getProducts().keySet()){
            sup.addInstrument(i);
        }
        sup.start();

        rf.setSupplier(sup);

        rf.sellInstrument("Organ", 1);
    }
}
