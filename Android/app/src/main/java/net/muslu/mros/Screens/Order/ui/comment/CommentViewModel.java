package net.muslu.mros.Screens.Order.ui.comment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CommentViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public CommentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is comment fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
