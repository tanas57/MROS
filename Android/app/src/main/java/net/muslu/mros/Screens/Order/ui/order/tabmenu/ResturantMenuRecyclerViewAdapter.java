package net.muslu.mros.Screens.Order.ui.order.tabmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.muslu.mros.Models.Product;
import net.muslu.mros.Models.ProductCategory;
import net.muslu.mros.R;


import java.util.ArrayList;
import java.util.List;

public class ResturantMenuRecyclerViewAdapter extends RecyclerView.Adapter<ResturantMenuRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<ProductCategory> productCategories;

    public ResturantMenuRecyclerViewAdapter(Context context, ArrayList<ProductCategory> productCategories) {
        this.context = context;
        this.productCategories = productCategories;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.product_cat_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductCategory category = this.productCategories.get(position);
        ArrayList<Product> products = (ArrayList<Product>)category.getProducts();
        holder.productCatName.setText(category.getCatName());
        holder.products.setAdapter(new CatProductDisplayAdapter(context, products));
        holder.products.setLayoutManager(new LinearLayoutManager(context));
        holder.products.setHasFixedSize(false);
        //Picasso.with(mContext).load(barcodeReadModel.getBarcodeImgApiURL()).into(holder.packageBarcode);
    }

    @Override
    public int getItemCount() {
        return productCategories.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder{

        TextView productCatName;
        RecyclerView products;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productCatName = itemView.findViewById(R.id.categoryName);
            products = itemView.findViewById(R.id.products);
        }
    }
}
