package com.company;

public class Main {

    public static void main(String[] args) {

        Store lidl = new Store("lidl", "cherni vrah", 2000, 10);
        ProductByNumber beer = new ProductByNumber("beer", 1, 20);
        lidl.addProduct(beer);
        ProductByWeight miso = new ProductByWeight("miso", 1.5, 30);
        lidl.addProduct(miso);

        lidl.listAllProducts();
        User miro = new User(lidl, 400, 5);
        miro.addProductByNumberToCart(beer, 15);
        miro.addProductByWeightToCart(miso, 15);
        miro.showCart();
        lidl.listAllProducts();
        System.out.println();
        System.out.println(lidl.getMoneyInRegister());
        System.out.println(miro.getMoney());

        miro.checkout();
        System.out.println(lidl.getMoneyInRegister());
        System.out.println(miro.getMoney());
    }
}
