package com.androidexam.approvalmatrix.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.androidexam.approvalmatrix.model.ApprovalMatrix;
import com.androidexam.approvalmatrix.model.ApprovalMatrixDatabase;
import com.androidexam.approvalmatrix.utilities.ApproverRecyclerViewAdapterCrUp;

import java.util.List;

public class CreateFragmentViewModel {

    private ApprovalMatrix approvalMatrix;
    private String[] items = {"Feature", "Default", "Transfer Online"};
    private MutableLiveData<String> mSelectedItem = new MutableLiveData<>();
    private MutableLiveData<Integer> mItemCountLiveData = new MutableLiveData<>(0);
    private MutableLiveData<Boolean> buttonAddClicked = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> buttonResetClicked = new MutableLiveData<>(false);


    private ApproverRecyclerViewAdapterCrUp adapter;

    private Context context;

    public CreateFragmentViewModel(Context context) {
        this.context = context;
    }

    public String[] getSuggestions() {
        return items;
    }

    public MutableLiveData<String> getSelectedItem() {
        return mSelectedItem;
    }
    public void setSelectedItem(String selectedItem) {
        mSelectedItem.setValue(selectedItem);
    }

    public MutableLiveData<Boolean> getButtonAddClicked() {
        return buttonAddClicked;
    }

    public MutableLiveData<Boolean> getButtonResetClicked() {
        return buttonResetClicked;
    }


    public MutableLiveData<Integer> getItemCountLiveData() {
        return mItemCountLiveData;
    }

    public void setItemCount(int count) {
        mItemCountLiveData.setValue(count);
        adapter = new ApproverRecyclerViewAdapterCrUp(context, getId(), count);
    }

    public int getId()
    {
        ApprovalMatrix lastApprovalMatrix = ApprovalMatrixDatabase.getInstance(context.getApplicationContext()).matrixDAO().getLastApprovalMatrix();
        int lastId = lastApprovalMatrix != null ? lastApprovalMatrix.getMatrixId() : 0;
        return lastId + 1;
    }

    public ApproverRecyclerViewAdapterCrUp getAdapter() {
        return adapter;
    }

    public void onButtonAddClicked()
    {
        buttonAddClicked.setValue(true);
    }

    public void onButtonResetClicked()
    {
        buttonResetClicked.setValue(true);
    }
}
