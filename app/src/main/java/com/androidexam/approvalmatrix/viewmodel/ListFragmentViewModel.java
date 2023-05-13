package com.androidexam.approvalmatrix.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidexam.approvalmatrix.model.Approver;
import com.androidexam.approvalmatrix.model.ApproverDAO;

public class ListFragmentViewModel{
    private MutableLiveData<Boolean> buttonAddClicked = new MutableLiveData<>();

    private ApproverDAO approverDAO;

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
