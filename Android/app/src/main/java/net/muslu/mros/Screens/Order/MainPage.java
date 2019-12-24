package net.muslu.mros.Screens.Order;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import net.muslu.mros.Api.HtmlProcces;
import net.muslu.mros.Api.LinkHelper;
import net.muslu.mros.Data;
import net.muslu.mros.Models.Basket;
import net.muslu.mros.Models.CustomerFeed;
import net.muslu.mros.Models.Product;
import net.muslu.mros.Models.ProductCategory;
import net.muslu.mros.Models.Restaurant;
import net.muslu.mros.MrosData;
import net.muslu.mros.R;
import net.muslu.mros.Screens.Order.ui.basket.BasketFragment;
import net.muslu.mros.Screens.Order.ui.comment.CommentFragment;
import net.muslu.mros.Screens.Order.ui.order.OrderFragment;
import net.muslu.mros.Screens.Order.ui.other.OtherFragment;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity implements MrosData {

    private Restaurant restaurant;
    private Gson gson;
    protected Restaurant getRestaurant() {
        return restaurant;
    }
    protected void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    protected ProgressDialog dialog;

    protected OrderFragment orderFragment;
    protected CommentFragment commentFragment;
    protected BasketFragment basketFragment;
    protected OtherFragment otherFragment;

    protected List<ProductCategory> productCategories;
    protected BottomNavigationView navView;
    protected ArrayList<Product> products;
    protected Basket basket;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog.dismiss();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Basket basket1 = Data.get(getApplicationContext());
        if(basket1 != null) basket = basket1;

        ArrayList<Product> products = basket.getProducts();

        for (Product item : products) {

            Log.v("BASKET", item.getName() + " " + item.getPreparation());
        }
    }

    protected void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // initialize objects
        gson = new GsonBuilder().create();
        products = new ArrayList<>();
        basket = new Basket();

        Data.set(basket, getApplicationContext());

        //result = findViewById(R.id.result);
        navView = findViewById(R.id.nav_view);

        // navigation_basket

        //navView.getOrCreateBadge(R.id.navigation_basket).setNumber(1);

        if( getIntent().getExtras() != null){
            setRestaurant((Restaurant)getIntent().getExtras().get("restaurant"));
        }else{
            String res = "{\"id\":1,\"fullName\":\"Göl Pastanesi\",\"address\":\"Adatepe, Kıbrıs Cd. No:34, 35400 Buca/İzmir | Yabancı diller fakültesi kampüsü yakını\",\"phone\":\"0 232 555 3322\",\"logo\":null,\"photo\":\"gol-pastanesi.jpg\",\"information\":\"Pelit ailesi yıllar önce Rize Çamlıhemşin’den Kırım’a çalışmaya gitti. Bölge, pastacılıkta çok ünlüydü. Pastacılığın bütün inceliklerini öğrendikten sonra kendi pastane zincirini kurdu. Ancak Bolşevik İhtilali ile birlikte memleketleri Rize’ye geri döndüler.\\r\\n\\r\\n1946’da gittikleri Zonguldak’ta Balkaya Pastanesi’nde çalışmaya başladılar ve kısa bir süre sonra pastanenin işletmesini aldılar. Pastacılığın inceliklerini İstanbul’daki Moskova Pastanesi’nin yetişmiş ustalarından öğrendiler. Zonguldak’ın ardından atılımları için 1954’te İzmir’i tercih ettiler. İzmir Kıbrıs Şehitleri Caddesi’nde önce Ülkü Pastanesi’ni açan Pelit ailesi, 1957 yılında da Sevinç Pastanesi’ni açtılar.\\r\\n\\r\\nTürkiye’nin çeşitli şehirlerinden en meşhur pasta, şekerleme, tatlı ustalarını transfer ederek; izmirlileri, baton muzlu pasta, çilekli pasta, turta, kestane şekeri, meyve şekerlemeleri, badem kurabiyesi, dilim pasta, krem şanti ve meyveli pasta gibi yeni lezzetlerle tanıştırdılar. İzmirliler tarafından büyük ilgi gören Sevinç Pastanesi, İzmir’in sembolü ve buluşma noktası haline geldi.\\r\\n\\r\\nGünümüzde ailenin ikinci ve üçüncü kuşakları aldıkları bayrağı yeni lezzetler de katarak geleceğe taşımaktadırlar.\\r\\n\\r\\nTürkiye’nin neresine gidilirse gidilsin mutlaka Sevinç lezzetlerini tatmış biri vardır.\",\"latitude\":37.0001,\"longitude\":27.0002,\"status\":true,\"orderCount\":0,\"totalGain\":0.0}";

            setRestaurant(gson.fromJson(res, Restaurant.class));
        }

        setTitle(getRestaurant().getFullName() + " | " + getString(R.string.app_name));;

        dialog = new ProgressDialog(MainPage.this);
        orderFragment = new OrderFragment();
        basketFragment = new BasketFragment();
        commentFragment = new CommentFragment();
        otherFragment = new OtherFragment();

        new GetCategories().execute();

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.navigation_order:
                        setFragment(orderFragment);
                        return true;
                    case R.id.navigation_basket:
                        setFragment(basketFragment);
                        return true;
                    case R.id.navigation_comment:
                        setFragment(commentFragment);
                        return true;
                    case R.id.navigation_other:
                        setFragment(otherFragment);
                        return true;
                }


                return false;
            }
        });
    }

    @Override
    public Restaurant getCurrentRestaurant() {
        return getRestaurant();
    }

    protected class GetCategories extends AsyncTask<String, String, List<ProductCategory>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected List<ProductCategory> doInBackground(String... strings) {
            List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
            try{
                String jsonObject = HtmlProcces.getJsonData
                        (LinkHelper.GetLink(Integer.toString(restaurant.getId()), LinkHelper.LinkType.LIST_PRODUCT_CATEGORIES),
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
                    //Log.v("CAT_NAME => ", item.getCatName() + " ID => " + item.getId());
                    List<Product> products = new ArrayList<Product>();
                    String jsonObject = HtmlProcces.getJsonData
                            (LinkHelper.GetLink(Integer.toString(item.getId()), LinkHelper.LinkType.LIST_PRODUCTS),
                                    "CAT_PRODUCTS");
                    JSONArray cats = new JSONArray(jsonObject);
                    for(int i = 0; i < cats.length(); i++){
                        JSONObject temp = cats.getJSONObject(i);

                        Product product = gson.fromJson(temp.toString(), Product.class);
                        products.add(product);
                        //Log.v("PRODUCT_NAME" , product.getName());
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
            dialog.hide();
            String rs = "";
            for (ProductCategory cat : productCategories){
                if(cat.getProductCount()>0) {
                    rs += "\n\n" +cat.getCatName() + "\n\n";
                    for (Product product : cat.getProducts()) {
                        rs += "" + product.getName() + " " + product.getPrice() + "\n";
                    }
                }
            }
            //result.setText(rs);

            setProductCategories(productCategories);
            Bundle bundle = new Bundle();
            bundle.putSerializable("categories", (ArrayList<ProductCategory>) productCategories);
            orderFragment.setArguments(bundle);
            setFragment(orderFragment);

        }
    }
    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    @Override
    public ArrayList<ProductCategory> getCurrentProductCategories() {
        return (ArrayList)getProductCategories();
    }

    @Override
    public ArrayList<CustomerFeed> getCurrentComments() {
        return null;
    }

    @Override
    public Basket getBasket() {
        return basket;
    }


}
