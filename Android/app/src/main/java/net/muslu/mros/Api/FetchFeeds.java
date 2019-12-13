package net.muslu.mros.Api;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import net.muslu.mros.Models.CustomerFeed;
import net.muslu.mros.Models.Product;
import net.muslu.mros.Models.ProductCategory;
import net.muslu.mros.Models.Restaurant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static net.muslu.mros.Api.LinkHelper.LinkType.CUSTOMER_FEEDS;

public class FetchFeeds extends AsyncTask<Restaurant, ArrayList<CustomerFeed>, ArrayList<CustomerFeed>> {

    @Override
    protected ArrayList<CustomerFeed> doInBackground(Void... lists) {
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

    @Override
    protected ArrayList<CustomerFeed> doInBackground(Restaurant... restaurants) {
        try{
            String url = LinkHelper.GetLink(Integer.toString(restaurants[0].getID()), CUSTOMER_FEEDS);
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
}