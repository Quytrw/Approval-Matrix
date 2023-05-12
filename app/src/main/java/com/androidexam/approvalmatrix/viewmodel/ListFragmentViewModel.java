package com.androidexam.approvalmatrix.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListFragmentViewModel{
    private MutableLiveData<Boolean> buttonAddClicked = new MutableLiveData<>();

    public ListFragmentViewModel() {
    }

    public void onButtonAddClicked()
    {
        buttonAddClicked.setValue(true);
    }

    public MutableLiveData<Boolean> getButtonAddClicked() {
        return buttonAddClicked;
    }
}
