package net.muslu.mros.Screens.Order.ui.order;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.tabs.TabLayout;
import net.muslu.mros.R;
import net.muslu.mros.Screens.Order.ui.order.tabmenu.OrderPagerAdapter;

public class OrderFragment extends Fragment {

    private OrderViewModel orderViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

        View root = inflater.inflate(R.layout.order_fragment, container, false);

        final Bundle bundle = getArguments();
        if(bundle != null){
            TabLayout tabLayout = root.findViewById(R.id.tabmenubar);
            tabLayout.addTab(tabLayout.newTab().setText(R.string.page_order_menu));
            tabLayout.addTab(tabLayout.newTab().setText(R.string.page_order_info));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            final ViewPager viewPager = root.findViewById(R.id.view_pager);
            PagerAdapter pagerAdapter = new OrderPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount(), bundle);
            viewPager.setAdapter(pagerAdapter);

            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                    viewPager.setCurrentItem(tab.getPosition());

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
        else Log.v("ORDER FRAGMEN", "NULL BUNDLE");

        return root;
    }

    protected void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.view_pager, fragment);
        fragmentTransaction.commit();
    }

}
