package net.muslu.mros.Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Customer implements Serializable {
    private int id;
    private String fullName;
    private String phone;
    private String address;
    private String birhdate;
    private boolean sex;
    private String regDateTime;
    private String profilePhoto;
    private int orderCount;
    private boolean status;

    public Customer(int id, String fullName, String phone,
                    String address, String birthdate, boolean sex, String regDateTime,
                    String profilePhoto, int orderCount, boolean status) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.birhdate = birthdate;
        this.sex = sex;
        this.regDateTime = regDateTime;
        this.profilePhoto = profilePhoto;
        this.orderCount = orderCount;
        this.status = status;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdate() {
        return birhdate;
    }

    public void setBirthdate(String birthdate) {
        this.birhdate = birthdate;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getRegDateTime() {
        return regDateTime;
    }

    public void setRegDateTime(String regDateTime) {
        this.regDateTime = regDateTime;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
