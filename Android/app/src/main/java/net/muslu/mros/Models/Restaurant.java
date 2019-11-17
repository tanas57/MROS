package net.muslu.mros.Models;

public class Restaurant {
    protected int ID;
    protected String FullName;
    protected String Address;
    protected String Phone;
    protected String Logo;
    protected String Information;
    //protected LatLng Coordinate;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }
}
