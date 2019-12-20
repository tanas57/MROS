package net.muslu.mros.Screens.Order.ui.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import net.muslu.mros.Data;
import net.muslu.mros.Models.Basket;
import net.muslu.mros.Models.Product;
import net.muslu.mros.MrosData;
import net.muslu.mros.R;

import java.util.ArrayList;

public class ProductDetail extends AppCompatActivity {

    private Product product;
    private int count = 1;
    private TextView num, price;
    Basket basket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent();

        if(intent != null){
            Bundle bundle = intent.getExtras();
            if(bundle != null){
                product = (Product) bundle.getSerializable("product");
                if(product == null) return;

                getSupportActionBar().setTitle(getString(R.string.product_detail));
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                basket = Data.get(getApplicationContext());

            }
        }

        TextView name = findViewById(R.id.product_name);
        TextView detail = findViewById(R.id.product_detail);
        num    = findViewById(R.id.product_count);
        price  = findViewById(R.id.product_price);

        price.setText((count*product.getPrice()) + " " + getString(R.string.money_type));

        Button increase, decrease, add;

        num.setText(count + " " + getString(R.string.num_of_product));

        increase = findViewById(R.id.product_increase);
        decrease = findViewById(R.id.product_decrease);
        add      = findViewById(R.id.product_add_button);

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                num.setText(count + " " + getString(R.string.num_of_product));
                price.setText((count*product.getPrice()) + " " + getString(R.string.money_type));
            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count > 1) {
                    count--;
                    num.setText(count + " " + getString(R.string.num_of_product));
                    price.setText((count*product.getPrice()) + " " + getString(R.string.money_type));
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setPreparation(count);
                basket.addProduct(product);
                onBackPressed();
            }
        });


        name.setText(product.getName());
        detail.setText(product.getName() + " " + product.getName() + " " + product.getName() + " " + product.getName() + " " + product.getName() + " " );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Data.set(basket, getApplicationContext());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }
}
