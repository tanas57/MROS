package net.muslu.mros.Screens.Order;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import net.muslu.mros.Api.HtmlProcces;
import net.muslu.mros.Api.LinkHelper;
import net.muslu.mros.Models.Product;
import net.muslu.mros.Models.ProductCategory;
import net.muslu.mros.Models.Restaurant;
import net.muslu.mros.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainPage extends AppCompatActivity {

    private Restaurant restaurant;
    private Gson gson;
    protected Restaurant getRestaurant() {
        return restaurant;
    }
    private TextView result;
    protected void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        gson = new GsonBuilder().create();

        if( getIntent().getExtras() != null){
            setRestaurant((Restaurant)getIntent().getExtras().get("restaurant"));
        }
        else{
            setRestaurant(new Restaurant(1, "Göl Pastanesi","Yabancı diller fakültesi kampüsü yakını","2325553322",null,"Ekmek ve unlu mamuller, tatlı vs."));
        }
        new GetCategories().execute();
        result = findViewById(R.id.result);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_order,R.id.navigation_basket,R.id.navigation_comment, R.id.navigation_other)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    protected class GetCategories extends AsyncTask<String, String, List<ProductCategory>>{

        @Override
        protected List<ProductCategory> doInBackground(String... strings) {
            List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
            try{
                String jsonObject = HtmlProcces.getJsonData
                        (LinkHelper.GetLink(Integer.toString(restaurant.getID()), LinkHelper.LinkType.LIST_PRODUCT_CATEGORIES),
                                "PRODUCT_CATS");
                JSONArray cats = new JSONArray(jsonObject);
                for(int i = 0; i < cats.length(); i++){
                    JSONObject temp = cats.getJSONObject(i);

                    ProductCategory productCategory = gson.fromJson(temp.toString(), ProductCategory.class);
                    productCategories.add(productCategory);
                    Log.v("CATEGORY_NAME" , productCategory.getId() + " " + productCategory.getCatName());
                }


            }catch (Exception e){
                e.printStackTrace();
            }
            return productCategories;
        }

        @Override
        protected void onPostExecute(List<ProductCategory> categories) {
            super.onPostExecute(categories);
            new GetProducts().execute(categories);
        }
    }
    protected class GetProducts extends AsyncTask<List<ProductCategory>, String, List<ProductCategory>>{

        @Override
        protected List<ProductCategory> doInBackground(List<ProductCategory>... lists) {
            try{
                List<ProductCategory> categories = lists[0];
                for(ProductCategory item : categories){
                    Log.v("CAT_NAME => ", item.getCatName() + " ID => " + item.getId());
                    List<Product> products = new ArrayList<Product>();
                    String jsonObject = HtmlProcces.getJsonData
                            (LinkHelper.GetLink(Integer.toString(item.getId()), LinkHelper.LinkType.LIST_PRODUCTS),
                                    "CAT_PRODUCTS");
                    JSONArray cats = new JSONArray(jsonObject);
                    for(int i = 0; i < cats.length(); i++){
                        JSONObject temp = cats.getJSONObject(i);

                        Product product = gson.fromJson(temp.toString(), Product.class);
                        products.add(product);
                        Log.v("PRODUCT_NAME" , product.getName());
                    }
                    item.setProducts(products);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return lists[0];
        }

        @Override
        protected void onPostExecute(List<ProductCategory> productCategories) {
            super.onPostExecute(productCategories);
            String rs = "";
            for (ProductCategory cat : productCategories){
                if(cat.getProductCount()>0) {
                    rs += "\n\n" +cat.getCatName() + "\n\n";
                    for (Product product : cat.getProducts()) {
                        rs += "" + product.getName() + " " + product.getPrice() + "\n";
                    }
                }
            }
            result.setText(rs);
        }
    }

}
