
package net.muslu.mros.Screens.Order.ui.comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import net.muslu.mros.Models.CustomerFeed;
import net.muslu.mros.R;

import java.util.ArrayList;

public class CommentListviewAdapter extends ArrayAdapter<CustomerFeed> {

    int resource;
    ArrayList<CustomerFeed> feeds;

    public CommentListviewAdapter(Context context, int resource, ArrayList<CustomerFeed> objects) {
        super(context, resource, objects);
        this.resource = resource;
        feeds = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(resource, parent, false);

        TextView name = convertView.findViewById(R.id.comment_fullname);
        TextView point = convertView.findViewById(R.id.comment_points);
        TextView message = convertView.findViewById(R.id.comment_message);

        //name.setText(feeds.get(position).getOwner().getFullname());

        name.setText("kullanıcı bilgisi çek");
        point.setText(feeds.get(position).getRatingFlavor() + " " + feeds.get(position).getRatingService()
                + " " + feeds.get(position).getRatingWaiter() + " " + feeds.get(position).getRatingWaiter());
        message.setText(feeds.get(position).getMessage());
        return convertView;
    }
}