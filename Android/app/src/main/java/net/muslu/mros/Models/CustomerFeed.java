package net.muslu.mros.Models;

import java.io.Serializable;

public class CustomerFeed implements Serializable {
    protected int id;
    protected Customer customer;
    protected double ratingService;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getRatingService() {
        return ratingService;
    }

    public void setRatingService(double ratingService) {
        this.ratingService = ratingService;
    }

    public double getRatingFlavor() {
        return ratingFlavor;
    }

    public void setRatingFlavor(double ratingFlavor) {
        this.ratingFlavor = ratingFlavor;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(double serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    protected double ratingFlavor;
    protected double serviceTime;
    protected String message;
}
