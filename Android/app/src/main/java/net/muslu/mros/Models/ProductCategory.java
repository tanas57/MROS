package net.muslu.mros.Models;

import java.io.Serializable;
import java.util.List;

public class ProductCategory implements Serializable {
    protected int id;
    protected String catName;
    protected String catIMG;
    protected boolean status;
    protected int counter;
    protected List<Product> products;

    public ProductCategory(int id, String catName, String catIMG, boolean status, int counter) {
        setId(id);
        setCatName(catName);
        setCatIMG(catIMG);
        setStatus(status);
        setCounter(counter);
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    protected void setCatName(String catName) {
        catName = catName;
    }

    public String getCatIMG() {
        return catIMG;
    }

    protected void setCatIMG(String catIMG) {
        catIMG = catIMG;
    }

    public boolean isStatus() {
        return status;
    }

    protected void setStatus(boolean status) {
        status = status;
    }

    public int getCounter() {
        return counter;
    }

    protected void setCounter(int counter) {
        this.counter = counter;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getProductCount(){ return this.products.size(); }
}
