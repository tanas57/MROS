package net.muslu.mros.Screens.Order.ui.order.tabmenu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import net.muslu.mros.Models.ProductCategory;
import net.muslu.mros.R;
import java.util.ArrayList;

public class RestaurantMenu extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.order_section_menu, container, false);

        RecyclerView restaurantMenu = root.findViewById(R.id.restaurantMenu);

        Bundle bundle = getArguments();
        if(bundle != null){

            ArrayList<ProductCategory> categories = (ArrayList<ProductCategory>)bundle.getSerializable("categories");
            restaurantMenu.setAdapter(new ResturantMenuRecyclerViewAdapter(getContext(), categories));
            restaurantMenu.setLayoutManager(new LinearLayoutManager(getContext()));
            restaurantMenu.setHasFixedSize(false);
        }else{
            Log.v("RESTAURANT_MENU_BUNDLE", "NULL DATA");
        }



        return root;
    }
}
