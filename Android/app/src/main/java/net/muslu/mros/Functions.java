package net.muslu.mros;

import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;

import static androidx.core.content.ContextCompat.getSystemService;

public final class Functions {
    public static boolean isNetworkConnected(Context context) {
        //ConnectivityManager cm = (ConnectivityManager) getSystemService(context);
        //return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
        return false;
    }
}
