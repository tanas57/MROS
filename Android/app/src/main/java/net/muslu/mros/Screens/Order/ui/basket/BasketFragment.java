package net.muslu.mros.Screens.Order.ui.basket;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.muslu.mros.Data;
import net.muslu.mros.Models.Basket;
import net.muslu.mros.Models.Product;
import net.muslu.mros.R;
import net.muslu.mros.Screens.Order.ui.comment.CommentFragment;

import java.util.ArrayList;

public class BasketFragment extends Fragment {

    private BasketViewModel basketViewModel;
    private RecyclerView user_basket;
    private TextView total_cost;

    private Basket basket;

    public static CommentFragment newInstance() {
        return new CommentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        basketViewModel =
                ViewModelProviders.of(this).get(BasketViewModel.class);


        basket = getdata();

        if(basket == null){
            View empty_basket = inflater.inflate(R.layout.empty_basket_layout, container, false);

            return empty_basket;
        }else{

            View root = inflater.inflate(R.layout.basket_fragment, container, false);

            user_basket = root.findViewById(R.id.user_basket);
            user_basket.setLayoutManager(new LinearLayoutManager(getContext()));

            ArrayList<Product> products = basket.getProducts();

            total_cost = root.findViewById(R.id.basket_total_cost);
            Button ok = root.findViewById(R.id.basket_ok);

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "your order : ", Toast.LENGTH_LONG).show();;
                }
            });

            Log.v("BASKET GET DATA", " NUM OF " + products.size());
            BasketAdapter basketAdapter = new BasketAdapter(getContext(), products, new BasketAdapter.ClickListener() {
                @Override
                public void onPositionClicked(View view, Product product, int pos) {

                }
            });
            user_basket.setAdapter(basketAdapter);
            String temp = basket.getTotalCost() + " TL";
            total_cost.setText(temp);


            return root;

        }
    }

    private Basket getdata(){
        try{
            Basket basket = Data.get(getContext());
            Log.v("BASKET GET DATA", "TRY TO GET DATA");
            if(basket != null) {
                ArrayList<Product> products = basket.getProducts();
                if (products.size() > 0) {

                    return basket;
                }
            }
        }
        catch (Exception e){e.printStackTrace();}
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        getdata();
    }
}
