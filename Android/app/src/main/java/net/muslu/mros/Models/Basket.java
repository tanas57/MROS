package net.muslu.mros.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Basket implements Serializable {
    protected ArrayList<Product> products;
    protected Restaurant restaurant;

    public Basket(){
        products = new ArrayList<>();
        restaurant = null;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void remProduct(Product product) {
        this.products.remove(product);
    }

    public double getTotalCost(){
        double result = 0;

        for (Product item : products){
            result += item.getPreparation() * item.getPrice();
        }
        return result;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
