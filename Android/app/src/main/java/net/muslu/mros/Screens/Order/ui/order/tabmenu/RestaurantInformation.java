package net.muslu.mros.Screens.Order.ui.order.tabmenu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import net.muslu.mros.Api.LinkHelper;
import net.muslu.mros.Models.Restaurant;
import net.muslu.mros.MrosData;
import net.muslu.mros.R;
import java.io.InputStream;
import java.util.ArrayList;

public class RestaurantInformation extends Fragment {
    MrosData mrosData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.order_section_information, container, false);

        ImageView photo = root.findViewById(R.id.restaurantPhoto);
        ListView info = root.findViewById(R.id.restaurantinfoList);

        mrosData = (MrosData)getActivity();
        if(mrosData == null) Log.v("MROS DATA", "null data");
        else {
            Log.v("MROS DATA", mrosData.getCurrentRestaurant().getFullName());
            Restaurant restaurant = mrosData.getCurrentRestaurant();

            ArrayList<RestaurantInfo> restaurantInfos = new ArrayList<>();
            restaurantInfos.add(new RestaurantInfo(R.drawable.ic_phone, restaurant.getPhone()));
            restaurantInfos.add(new RestaurantInfo(R.drawable.ic_location_on, restaurant.getAddress()));
            restaurantInfos.add(new RestaurantInfo(R.drawable.ic_info_outline, restaurant.getInformation()));

            RestaurantInfoAdapter restaurantInfoAdapter = new RestaurantInfoAdapter(getContext(),R.layout.order_section_information_list_layout, restaurantInfos);
            info.setAdapter(restaurantInfoAdapter);

            new DownloadImageTask((ImageView) photo)
                    .execute(LinkHelper.GetLink(Integer.toString(restaurant.getID()), LinkHelper.LinkType.RESTAURANT_IMG));
        }

        return root;
    }

    public class RestaurantInfo{
        private int image;
        private String text;

        public RestaurantInfo(int image, String text) {
            this.image = image;
            this.text = text;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
    // show The Image in a ImageView

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
