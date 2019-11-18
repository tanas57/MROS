package net.muslu.mros.Screens.StartupScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.muslu.mros.R;
import net.muslu.mros.Screens.BarcodeRead;


public class EnterRestaurant extends AppCompatActivity  {

    private ViewPager viewPager;
    private LinearLayout dotLayout;
    private TextView[] dots;
    private SliderAdaptor sliderAdaptor;
    private Button next, previous;
    private int pageCounter = 0;
    private String nextButton = "İleri", finishButton = "Bitir";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_restaurant);

        viewPager = findViewById(R.id.slider);
        dotLayout = findViewById(R.id.dots);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);

        sliderAdaptor = new SliderAdaptor(getApplicationContext());
        viewPager.setAdapter(sliderAdaptor);
        addDots();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageCounter = position;
                makeDotSelected(position);
                if(position == 0) {
                    previous.setVisibility(View.INVISIBLE);
                    next.setVisibility(View.VISIBLE);
                }
                else if(position == sliderAdaptor.getCount() - 1){
                    next.setText(finishButton);
                }
                else{
                    previous.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                }
                if(position != sliderAdaptor.getCount() - 1) next.setText(nextButton);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(next.getText().equals(finishButton)){ // open camera to reading barcode
                    Intent barcodeRead = new Intent(getApplicationContext(), BarcodeRead.class);
                    startActivity(barcodeRead);
                }
                viewPager.setCurrentItem(pageCounter + 1);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(pageCounter - 1);
            }
        });
    }

    public void makeDotSelected(int position){
        for (int i = 0; i < dots.length; i++){
            if(i == position) dots[i].setTextColor(getResources().getColor(R.color.colorAccent));
            else dots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
        }
    }
    public void addDots(){
        dots = new TextView[sliderAdaptor.getCount()];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            dotLayout.addView(dots[i]);
        }
        if(dots.length > 0)
            dots[0].setTextColor(getResources().getColor(R.color.colorAccent));
    }

}
