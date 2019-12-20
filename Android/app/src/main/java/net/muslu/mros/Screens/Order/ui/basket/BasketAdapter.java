package net.muslu.mros.Screens.Order.ui.basket;

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

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ProductViewHolder> {
        Context context;
        ArrayList<Product> products;
        private final ClickListener listener;
        private static WeakReference<ClickListener> listenerRef;

        public interface ClickListener {

            void onPositionClicked(View view, Product product, int pos);

        }

        public BasketAdapter(Context context, ArrayList<Product> products, ClickListener listener) {
            this.context = context;
            this.products = products;
            this.listener = listener;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(context).inflate(R.layout.basket_list_layout, parent, false);
            ProductViewHolder myViewHolder = new ProductViewHolder(v, listener);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            Product product = this.products.get(position);

            holder.cost.setText(product.getPreparation() * product.getPrice() + " TL");
            holder.name.setText(product.getName());
            holder.num.setText(product.getPreparation() + " ");
        }

        @Override
        public int getItemCount() {
            return products.size();
        }


        protected class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            TextView num, name, cost;

            public ProductViewHolder(@NonNull View itemView, ClickListener listener) {
                super(itemView);
                listenerRef = new WeakReference<>(listener);
                num = itemView.findViewById(R.id.basket_product_num);
                name = itemView.findViewById(R.id.basket_product_name);
                cost = itemView.findViewById(R.id.basket_product_cost);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                listenerRef.get().onPositionClicked(v, products.get(getAdapterPosition()),getAdapterPosition());
            }
        }
    }
