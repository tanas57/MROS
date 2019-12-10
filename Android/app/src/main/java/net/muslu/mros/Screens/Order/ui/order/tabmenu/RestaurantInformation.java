package net.muslu.mros.Screens.Order.ui.order.tabmenu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.muslu.mros.Models.ProductCategory;
import net.muslu.mros.R;

import java.util.ArrayList;

public class RestaurantInformation extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.order_section_information, container, false);

        ImageView photo = root.findViewById(R.id.restaurantPhoto);
        ListView info = root.findViewById(R.id.restaurantinfoList);

        Bundle bundle = getArguments();
        if(bundle != null){

            ArrayList<ProductCategory> categories = (ArrayList<ProductCategory>)bundle.getSerializable("categories");
           
        }else{
            Log.v("RESTAURANT_MENU_BUNDLE", "NULL DATA");
        }

        ArrayList<RestaurantInfo> restaurantInfos = new ArrayList<>();
        restaurantInfos.add(new RestaurantInfo(R.drawable.ic_phone, ""));


        return root;
    }

    private class RestaurantInfo{
        private int image;
        private String text;

        public RestaurantInfo(int image, String text) {
            this.image = image;
            this.text = text;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
