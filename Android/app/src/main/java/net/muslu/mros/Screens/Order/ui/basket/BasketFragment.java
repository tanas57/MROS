package net.muslu.mros.Screens.Order.ui.basket;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.muslu.mros.R;
import net.muslu.mros.Screens.Order.ui.comment.CommentFragment;

public class BasketFragment extends Fragment {

    private BasketViewModel basketViewModel;

    public static CommentFragment newInstance() {
        return new CommentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        basketViewModel =
                ViewModelProviders.of(this).get(BasketViewModel.class);
        View root = inflater.inflate(R.layout.basket_fragment, container, false);
        final TextView textView = root.findViewById(R.id.textview3);
        basketViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}
