package net.muslu.mros.Screens.Order.ui.order.tabmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import net.muslu.mros.R;

public class RestaurantMenu extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.order_section_menu, container, false);

        RecyclerView restaurantMenu = root.findViewById(R.id.restaurantMenu);

        restaurantMenu.setAdapter(new ResturantMenuRecyclerViewAdapter(getContext(), null));


        return root;
    }
}
