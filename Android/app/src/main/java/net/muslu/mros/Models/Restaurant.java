package net.muslu.mros.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Restaurant implements Serializable {

    protected int id;
    protected String fullName;
    protected String address;
    protected String phone;
    protected String logo;
    protected String photo;
    protected String information;
    protected double latitude;
    protected double longitude;
    protected boolean status;
    protected int orderCount;
    protected int totalGain;

    public Restaurant(int id, String fullName, String address, String phone,
                      String logo, String photo, String information, double latitude,
                      double longitude, boolean status, int orderCount, int totalGain) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.logo = logo;
        this.photo = photo;
        this.information = information;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.orderCount = orderCount;
        this.totalGain = totalGain;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getTotalGain() {
        return totalGain;
    }

    public void setTotalGain(int totalGain) {
        this.totalGain = totalGain;
    }
}
