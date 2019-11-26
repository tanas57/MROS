package net.muslu.mros.Screens.Order.ui.basket;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BasketViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public BasketViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is basket fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
