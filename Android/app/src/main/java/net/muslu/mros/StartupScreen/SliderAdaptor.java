package net.muslu.mros.StartupScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import net.muslu.mros.R;

/**
 * Created by MusluNET on 17.11.19
 */
public class SliderAdaptor extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    public int[] getSliderImgs() {
        return sliderImgs;
    }

    public void setSliderImgs(int[] sliderImgs) {
        this.sliderImgs = sliderImgs;
    }

    private int[] sliderImgs;
    private String[] sliderHeaders;
    private String[] sliderDescs;

    public String[] getSliderHeaders() {
        return sliderHeaders;
    }

    public void setSliderHeaders(String[] sliderHeaders) {
        this.sliderHeaders = sliderHeaders;
    }

    public String[] getSliderDescs() {
        return sliderDescs;
    }

    public void setSliderDescs(String[] sliderDescs) {
        this.sliderDescs = sliderDescs;
    }

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public SliderAdaptor(Context context) {
        setContext(context);
        setSliderImgs(new int[]{
                R.drawable.store,
                R.drawable.application,
                R.drawable.barcode
        });
        setSliderHeaders(new String[]{
                "Hoşgeldiniz", "Uygulama Kullanımı", "Hazırsanız Menüyü Görelim"
        });
        setSliderDescs(new String[]{
                "Merhaba Restorant sipariş uygulamasına hoşgeldiniz. Bizi tercihe ettiğiniz için teşekkür ederiz",
                "Restorantlardan sipariş verebilmek için oturduğunuz masa üzerinde bulunan barkodu kameranıza okutup ardından Restorant'ın menüsünü görüntüleyebilip ve sipariş verebilirsiniz",
                "Evet Restorant'ta masanıza oturdunuz ve sipariş vermek istiyorsunuz, hazırsanız kameranız açılacak; lütfen barkodu okutunuz keyifli alışverişler."
        });
    }

    @Override
    public int getCount() {
        return sliderHeaders.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView sliderImg = view.findViewById(R.id.sliderImage);
        TextView sliderTitle = view.findViewById(R.id.sliderTitle);
        TextView sliderDesc = view.findViewById(R.id.sliderContent);

        sliderImg.setImageResource(getSliderImgs()[position]);
        sliderTitle.setText(getSliderHeaders()[position]);
        sliderDesc.setText(getSliderDescs()[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
