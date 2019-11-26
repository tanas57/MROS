package net.muslu.mros.Models;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    protected int id;
    protected String name;
    protected double price;
    protected String img;
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
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        price = price;
    }

    public String getIMG() {
        return img;
    }

    public void setIMG(String IMG) {
        this.img = IMG;
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
