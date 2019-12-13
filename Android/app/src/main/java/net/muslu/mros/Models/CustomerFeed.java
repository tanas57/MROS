package net.muslu.mros.Models;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class CustomerFeed implements Serializable {
    protected int id;
    @Nullable
    protected Customer owner;
    @Nullable
    protected Restaurant restaurant;
    protected double ratingService;
    protected double ratingWaiter;
    protected double ratingFlavor;
    protected double serviceTime;
    protected String message;

    public CustomerFeed(int id, Customer owner, Restaurant restaurant, double ratingService, double ratingWaiter, double ratingFlavor, double serviceTime, String message) {
        this.id = id;
        this.owner = owner;
        this.restaurant = restaurant;
        this.ratingService = ratingService;
        this.ratingWaiter = ratingWaiter;
        this.ratingFlavor = ratingFlavor;
        this.serviceTime = serviceTime;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public double getRatingService() {
        return ratingService;
    }

    public void setRatingService(double ratingService) {
        this.ratingService = ratingService;
    }

    public double getRatingWaiter() {
        return ratingWaiter;
    }

    public void setRatingWaiter(double ratingWaiter) {
        this.ratingWaiter = ratingWaiter;
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
}
