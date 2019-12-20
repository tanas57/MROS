package net.muslu.mros;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import net.muslu.mros.Models.Basket;

public class Data {
    private static final String BASKET = "BASKET";
    static Gson gson = new Gson();
    public static void set(Basket value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        String json = gson.toJson(value);
        editor.putString(BASKET, json);
        editor.commit();
    }

    public static Basket get(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = preferences.getString(BASKET, null);
        Basket basket = gson.fromJson(json, Basket.class);
        return basket;
    }
}
