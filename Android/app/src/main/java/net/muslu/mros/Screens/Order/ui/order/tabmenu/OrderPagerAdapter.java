package net.muslu.mros.Screens.Order.ui.order.tabmenu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class OrderPagerAdapter extends FragmentPagerAdapter {

    int countTab;

    public OrderPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        countTab = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                RestaurantMenu restaurantMenu = new RestaurantMenu();
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
