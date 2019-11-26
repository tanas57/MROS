package net.muslu.mros.Models;

import java.io.Serializable;

public class Portion implements Serializable {
    protected int ID;
    protected String PortionName;
    protected double Cost;

    public Portion(int ID, String portionName, double cost) {
        setID(ID);
        setPortionName(portionName);
        setCost(cost);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPortionName() {
        return PortionName;
    }

    public void setPortionName(String portionName) {
        PortionName = portionName;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double cost) {
        Cost = cost;
    }
}
