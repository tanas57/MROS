package net.muslu.mros.Models;

import java.util.List;

public class ProductCategory {
    protected int id;
    protected String CatName;
    protected String CatIMG;
    protected boolean Status;
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
        return CatName;
    }

    protected void setCatName(String catName) {
        CatName = catName;
    }

    public String getCatIMG() {
        return CatIMG;
    }

    protected void setCatIMG(String catIMG) {
        CatIMG = catIMG;
    }

    public boolean isStatus() {
        return Status;
    }

    protected void setStatus(boolean status) {
        Status = status;
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

    protected void setProducts(List<Product> products) {
        this.products = products;
    }
}
