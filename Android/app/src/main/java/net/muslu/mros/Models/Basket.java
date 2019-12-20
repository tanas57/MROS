package net.muslu.mros.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Basket implements Serializable {
    protected ArrayList<Product> products;

    public Basket(){
        products = new ArrayList<>();
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
}
