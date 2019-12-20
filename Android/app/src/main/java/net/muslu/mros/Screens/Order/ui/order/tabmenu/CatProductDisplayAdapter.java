package net.muslu.mros.Screens.Order.ui.order.tabmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import net.muslu.mros.Models.Product;
import net.muslu.mros.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CatProductDisplayAdapter extends RecyclerView.Adapter<CatProductDisplayAdapter.ProductViewHolder> {
    Context context;
    ArrayList<Product> products;
    private final ClickListener listener;
    private static WeakReference<ClickListener> listenerRef;

    public interface ClickListener {

        void onPositionClicked(View view, Product product, int pos);

    }

    public CatProductDisplayAdapter(Context context, ArrayList<Product> products, ClickListener listener) {
        this.context = context;
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.cat_product_detail_layout, parent, false);
        ProductViewHolder myViewHolder = new ProductViewHolder(v, listener);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = this.products.get(position);

        holder.productName.setText(product.getName());
        holder.productContent.setText(product.getName() + " " + product.getName() + product.getName() + " " + product.getName() + product.getName() + " " + product.getName());
        holder.productCost.setText(product.getPrice() + " TL");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    protected class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView productName;
        TextView productContent;
        TextView productCost;
        public ProductViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);
            listenerRef = new WeakReference<>(listener);
            productName = itemView.findViewById(R.id.productName);
            productContent = itemView.findViewById(R.id.productContent);
            productCost = itemView.findViewById(R.id.productCost);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listenerRef.get().onPositionClicked(v, products.get(getAdapterPosition()),getAdapterPosition());
        }
    }
}
