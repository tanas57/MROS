package net.muslu.mros.Models;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.time.LocalTime;

public class CustomerFeed implements Serializable {

    /*
    {"id":1,
    "owner":null,
    "restaurant":null,
    "ratingService":9,
    "ratingWaiter":7,
    "ratingFlavor":8,
    "serviceTime":9,
    "message":"Oldukça lezzetli ve çeşit bol olan bir restorant, sürekli tercihimiz olmaya devam ediyot"}

     */
    protected int id;
    protected Customer owner;
    protected Restaurant restaurant;
    protected double ratingService;
    protected double ratingWaiter;
    protected double ratingFlavor;
    protected double serviceTime;
    protected String message;
    protected String feedDateTime;

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

    public String getFeedDateTime() {
        return feedDateTime;
    }

    public void setFeedDateTime(String feedDateTime) {
        this.feedDateTime = feedDateTime;
    }
}
