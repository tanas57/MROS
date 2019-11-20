package net.muslu.mros.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.Result;
import net.muslu.mros.Api.HtmlProcces;
import net.muslu.mros.Api.LinkHelper;
import net.muslu.mros.Models.Restaurant;
import net.muslu.mros.R;
import net.muslu.mros.Screens.Order.MainPage;

import org.json.JSONObject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class BarcodeRead extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_read);

        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.camera);

        mScannerView = new ZXingScannerView(this);

        PermissionCheck();

        contentFrame.addView(mScannerView);
    }

    private void PermissionCheck(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 500);
            return;
        }
    }
    @Override
    public void handleResult(final Result rawResult) {
        ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC,  5555);
        toneGen1.startTone(ToneGenerator.TONE_CDMA_ABBR_INTERCEPT,155);

        new FetchRestaurant().execute(rawResult.getText());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(BarcodeRead.this);
            }
        }, 333);

    }

    protected class FetchRestaurant extends AsyncTask<Object, Object, Restaurant> {
        @Override
        protected Restaurant doInBackground(Object... strings) {
            Restaurant restaurant = null;
            try{
                String jsonObject = HtmlProcces.getJsonData
                        (LinkHelper.GetLink(strings[0].toString(), LinkHelper.LinkType.FETCH_RESTAURANT_WITH_TABLE),
                                "QR_TABLE");
                Log.v("JSON_OUTPUT", jsonObject);
                Gson gson= new GsonBuilder().create();
                restaurant = gson.fromJson(new JSONObject(jsonObject).getJSONObject("restaurant").toString(), Restaurant.class);
                if(restaurant != null){
                    Log.v("RESTORAN_IN_SUCCESS", restaurant.getFullName() + " is successfully serialized");
                    Toast.makeText(getApplicationContext(), restaurant.getFullName() + getString(R.string.welcome), Toast.LENGTH_LONG).show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return restaurant;
        }

        @Override
        protected void onPostExecute(Restaurant s) {
            if(s.getID() > 0){
                Intent main_page = new Intent(BarcodeRead.this, MainPage.class);
                main_page.putExtra("restaurant", s);
                startActivity(main_page);
                // next page..
            }
            super.onPostExecute(s);
        }
    }


    @Override
    protected void onResume() {
        PermissionCheck();
        mScannerView.startCamera();
        mScannerView.setResultHandler(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
}
