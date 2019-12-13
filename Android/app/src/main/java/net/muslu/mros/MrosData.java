package net.muslu.mros;

import net.muslu.mros.Models.CustomerFeed;
import net.muslu.mros.Models.ProductCategory;
import net.muslu.mros.Models.Restaurant;

import java.util.ArrayList;

public interface MrosData {
    Restaurant getCurrentRestaurant();
    ArrayList<ProductCategory> getCurrentProductCategories();
    ArrayList<CustomerFeed> getCurrentComments();
}
