
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

import org.ocpsoft.prettytime.PrettyTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

        ImageView image = convertView.findViewById(R.id.user_photo);
        TextView name = convertView.findViewById(R.id.user_fullname);
        TextView point = convertView.findViewById(R.id.comment_score);
        TextView date = convertView.findViewById(R.id.comment_date);
        TextView message = convertView.findViewById(R.id.comment_message);

        //name.setText(feeds.get(position).getOwner().getFullname());

        CustomerFeed current = feeds.get(position);

        name.setText(current.getOwner().getFullName());
        point.setText( getContext().getString(R.string.comment_feed_flavor) + " " + (int)current.getRatingFlavor() + " " +
                            getContext().getString(R.string.comment_feed_service) + " " + (int)current.getRatingService() + " " +
                getContext().getString(R.string.comment_feed_waiter) + " " + " " + (int)current.getRatingWaiter() + " " +
                getContext().getString(R.string.comment_feed_flavor) + " " + (int)current.getRatingWaiter());
        message.setText(current.getMessage());
        PrettyTime p = new PrettyTime();
        p.setLocale(new Locale("tr"));

        String temp = current.getFeedDateTime();

        Date d = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            d = sdf.parse(temp);
        }
        catch (Exception e) { e.printStackTrace();}

        date.setText(p.format(d));
        return convertView;
    }
}