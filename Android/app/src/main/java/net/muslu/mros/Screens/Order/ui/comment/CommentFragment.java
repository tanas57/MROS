package net.muslu.mros.Screens.Order.ui.comment;

import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.muslu.mros.Api.HtmlProcces;
import net.muslu.mros.Api.LinkHelper;
import net.muslu.mros.Models.CustomerFeed;
import net.muslu.mros.Models.Restaurant;
import net.muslu.mros.MrosData;
import net.muslu.mros.R;
import net.muslu.mros.Screens.Order.ui.order.tabmenu.RestaurantInfoAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CommentFragment extends Fragment {

    private CommentViewModel mViewModel;
    protected ProgressDialog dialog;
    protected Restaurant restaurant;
    protected ArrayList<CustomerFeed> feeds;
    private Gson gson;
    private ListView comments;

    public static CommentFragment newInstance() {
        return new CommentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(CommentViewModel.class);
        View root = inflater.inflate(R.layout.comment_fragment, container, false);

        // 2019-12-13T00:14:34.7857092
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();


        comments = root.findViewById(R.id.comment);
        MrosData mrosData = (MrosData) getActivity();
        if(mrosData != null){
            Log.v("COMMENT FRAGMENT","MROS DATA NOT NULL");
            restaurant = mrosData.getCurrentRestaurant();
        }
        dialog = new ProgressDialog(getContext());
        new GetCustomerFeeds().execute();


        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dialog.dismiss();
    }

    protected class GetCustomerFeeds extends AsyncTask<String, String, ArrayList<CustomerFeed>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected ArrayList<CustomerFeed> doInBackground(String... strings) {
            ArrayList<CustomerFeed> customerFeeds = new ArrayList<>();
            try{
                String jsonObject = HtmlProcces.getJsonData
                        (LinkHelper.GetLink(Integer.toString(restaurant.getId()), LinkHelper.LinkType.CUSTOMER_FEEDS),
                                "CUSTOMER FEED JSON");
                JSONArray rows = new JSONArray(jsonObject);
                for(int i = 0; i < rows.length(); i++){
                    String temp = rows.get(i).toString();
                    CustomerFeed cf = gson.fromJson(temp, CustomerFeed.class);
                    customerFeeds.add(cf);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return customerFeeds;
        }

        @Override
        protected void onPostExecute(ArrayList<CustomerFeed> feeds) {
            super.onPostExecute(feeds);
            dialog.hide();
            CommentListviewAdapter restaurantInfoAdapter = new CommentListviewAdapter(getContext(),R.layout.comment_fragment_list_layout, feeds);
            comments.setAdapter(restaurantInfoAdapter);
            //setFeeds(feeds);
        }
    }

}
