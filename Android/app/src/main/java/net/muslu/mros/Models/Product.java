package net.muslu.mros.Models;

import java.util.List;

public class Product {
    protected int ID;
    protected String Name;
    protected double Price;
    protected String IMG;
    protected boolean isPortionable;
    protected List<Portion> portions;

    public Product(int ID, String name, double price, String IMG, boolean isPortionable, List<Portion> portions) {
        setID(ID);
        setName(name);
        setPrice(price);
        setIMG(IMG);
        setPortionable(isPortionable);
        setPortions(portions);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }

    public boolean isPortionable() {
        return isPortionable;
    }

    public void setPortionable(boolean portionable) {
        isPortionable = portionable;
    }

    public List<Portion> getPortions() {
        return portions;
    }

    public void setPortions(List<Portion> portions) {
        this.portions = portions;
    }
}
