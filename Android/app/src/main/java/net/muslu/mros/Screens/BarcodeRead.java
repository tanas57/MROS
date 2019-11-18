package net.muslu.mros.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.zxing.Result;
import net.muslu.mros.R;
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

        Toast.makeText(getApplicationContext(),rawResult.getText(), Toast.LENGTH_LONG).show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(BarcodeRead.this);
            }
        }, 333);

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
