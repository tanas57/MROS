package net.muslu.mros.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Restaurant implements Serializable {

    protected int id;
    protected String fullName;
    protected String address;
    protected String phone;
    protected String logo;
    protected String information;
    //protected LatLng Coordinate;

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        phone = phone;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        logo = logo;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        information = information;
    }
}
