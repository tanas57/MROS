package net.muslu.mros.Screens.Order.ui.order.tabmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import net.muslu.mros.R;
import java.util.ArrayList;
public class RestaurantInfoAdapter extends ArrayAdapter<RestaurantInformation.RestaurantInfo> {

        int resource;
        ArrayList<RestaurantInformation.RestaurantInfo> infos;
        public RestaurantInfoAdapter(Context context, int resource, ArrayList<RestaurantInformation.RestaurantInfo> objects) {
            super(context, resource, objects);
            this.resource = resource;
            infos = objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater =  LayoutInflater.from(getContext());
            convertView= inflater.inflate(resource, parent, false);

            ImageView infoImg = convertView.findViewById(R.id.info_image);
            TextView infoTxt = convertView.findViewById(R.id.info_text);

            infoImg.setImageResource(infos.get(position).getImage());
            infoTxt.setText(infos.get(position).getText());

            return convertView;
        }
    }