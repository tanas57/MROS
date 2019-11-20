package net.muslu.mros.Screens.Order;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import net.muslu.mros.Models.Restaurant;
import net.muslu.mros.R;

public class MainPage extends AppCompatActivity {

    private Restaurant restaurant;

    protected Restaurant getRestaurant() {
        return restaurant;
    }

    protected void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        if( getIntent().getExtras() != null){
            setRestaurant((Restaurant)getIntent().getExtras().get("restaurant"));
        }

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

}
