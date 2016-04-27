package com.company;

public class User {

    private Store store;
    private double money;
    private int freePlacesForItems;
    private Product[] cart = new Product[freePlacesForItems];

    public User(Store store, double money, int freePlacesForItems) {
        this.store = store;
        if (money > 0){
            this.money = money;
        } else {
            this.money = 0;
        }
        if (freePlacesForItems > 0){
            this.freePlacesForItems = freePlacesForItems;
        } else {
            this.freePlacesForItems = 0;
        }
        cart = new Product[freePlacesForItems];
    }

    public void buyProduct(Product product){
        if (money - product.getPrice() > 0){
            money -= product.getPrice();
        } else {
            System.out.println("User cannot afford this item.");
        }

    }

    public void addProductByWeightToCart(ProductByWeight product, double quantity){
        if (quantity <= product.getQuantity() && freePlacesForItems > 0){
            cart[cart.length - freePlacesForItems] = new ProductByWeight(product.getName(),product.getPrice(),quantity);
            product.setQuantity(product.getQuantity() - quantity);
            System.out.println("Added " + quantity + " of " + product.getName() + " to cart");
            freePlacesForItems--;
        } else {
            System.out.println("Can't add to cart.");
        }
    }

    public void addProductByNumberToCart(ProductByNumber product, int number){
        if (number <= product.getNumberOfItems() && freePlacesForItems > 0){
            cart[cart.length - freePlacesForItems] = new ProductByNumber(product.getName(), product.getPrice(), number);
            product.setNumberOfItems(product.getNumberOfItems() - number);
            System.out.println("Added " + number + " " + product.getName() + "s to cart.");
            freePlacesForItems--;
        } else {
            System.out.println("Can't add to cart.");
        }
    }

    public void removeProductByWeightFromCart(ProductByWeight product, double quantity){
        boolean hasProduct = false;
        ProductByWeight currentProduct = (ProductByWeight)cart[0];
        for (int i = 0; i < cart.length; i++) {
            if (cart[i] != null && cart[i] instanceof ProductByWeight){
                if (product.getName().equals(cart[i].getName())){
                    hasProduct = true;
                    currentProduct = (ProductByWeight)cart[i];
                    break;
                }
            }
        }
        if (hasProduct){
            if (quantity >= currentProduct.getQuantity()){
                quantity = currentProduct.getQuantity();
                product.setQuantity(product.getQuantity() + quantity);
                currentProduct.setQuantity(0);
            } else {
                product.setQuantity(product.getQuantity() + quantity);
                currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
            }
        }else {
            System.out.println("User does not have this product.");
        }
    }

    public void removeProductByNumberromCart(ProductByNumber product, int number){
        boolean hasProduct = false;
        ProductByNumber currentProduct = (ProductByNumber)cart[0];
        for (int i = 0; i < cart.length; i++) {
            if (cart[i] != null && cart[i] instanceof ProductByNumber){
                if (product.getName().equals(cart[i].getName())){
                    hasProduct = true;
                    currentProduct = (ProductByNumber)cart[i];
                    break;
                }
            }
        }
        if (hasProduct){
            if (number >= currentProduct.getNumberOfItems()){
                number = currentProduct.getNumberOfItems();
                product.setNumberOfItems(product.getNumberOfItems() + number);
                currentProduct.setNumberOfItems(0);
            } else {
                product.setNumberOfItems(product.getNumberOfItems() + number);
                currentProduct.setNumberOfItems(currentProduct.getNumberOfItems() - number);
            }
        }else {
            System.out.println("User does not have this product.");
        }
    }

    public void checkout(){
        double allPrices = 0;
        for (int i = 0; i < cart.length; i++) {
            if (cart[i] != null){
                if (cart[i] instanceof ProductByWeight){
                    allPrices += (cart[i].getPrice()*((ProductByWeight)cart[i]).getQuantity());
                } else if (cart[i] instanceof ProductByNumber){
                    allPrices += (cart[i].getPrice() * ((ProductByNumber)cart[i]).getNumberOfItems());
                }
            }
        }
        if (allPrices > money){
            System.out.println("User cannot pay for all the products.");
        } else {
            money -= allPrices;
            store.setMoneyInRegister(store.getMoneyInRegister() + allPrices);
            System.out.println("User bought all desired items.");
        }
    }

    public void showCart(){
        System.out.println("Products in cart:");
        for (int i = 0; i < cart.length; i++) {
            if (cart[i] != null){
                cart[i].showInfo();
            }
        }
    }

    public double getMoney() {
        return money;
    }
}
