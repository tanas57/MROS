package net.muslu.mros.Screens.Order.ui.order.tabmenu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import net.muslu.mros.Models.ProductCategory;

import java.util.ArrayList;

public class OrderPagerAdapter extends FragmentPagerAdapter {

    protected int countTab;
    protected Bundle bundle;

    public OrderPagerAdapter(@NonNull FragmentManager fm, int behavior, Bundle bundle) {
        super(fm, behavior);
        this.countTab = behavior;
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                RestaurantMenu restaurantMenu = new RestaurantMenu();
                restaurantMenu.setArguments(bundle);
                return restaurantMenu;

            case 1:
                RestaurantInformation restaurantInformation = new RestaurantInformation();
                return restaurantInformation;
        }
        return null;
    }

    @Override
    public int getCount() {
        return countTab;
    }
}
