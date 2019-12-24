package net.muslu.mros.Screens.Order.ui.basket;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import net.muslu.mros.Data;
import net.muslu.mros.Models.Basket;
import net.muslu.mros.Models.Product;
import net.muslu.mros.R;
import net.muslu.mros.Screens.MainActivity;
import net.muslu.mros.Screens.Order.MainPage;
import net.muslu.mros.Screens.Order.ui.comment.CommentFragment;

import java.util.ArrayList;

public class BasketFragment extends Fragment {

    private RecyclerView user_basket;
    private TextView total_cost;

    private Basket basket;
    private MainPage mainPage;
    @Override
    public void onAttach(@NonNull Activity activity) {
        mainPage = (MainPage)activity;
        super.onAttach(activity);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);

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
                if (products.size() > 0) return basket;
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.basket_top_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.basket_menu_empty)
        {
            if(basket == null){
                Toast.makeText(getContext(), "sepetiniz boş", Toast.LENGTH_SHORT).show();
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(getResources().getString(R.string.empty_basket));
                builder.setMessage(getResources().getString(R.string.ask_empty_basket));
                builder.setNegativeButton(getResources().getString(R.string.say_no), null);
                builder.setPositiveButton(getResources().getString(R.string.say_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        basket.getProducts().clear();
                        Data.set(basket,getContext());
                        getFragmentManager().beginTransaction().detach(BasketFragment.this).attach(BasketFragment.this).commit();
                    }
                });
                builder.show();
            }
        }
        return true;
    }
}
