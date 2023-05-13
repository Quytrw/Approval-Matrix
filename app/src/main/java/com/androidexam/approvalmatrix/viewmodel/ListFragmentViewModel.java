package com.androidexam.approvalmatrix.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidexam.approvalmatrix.model.ApprovalMatrixDatabase;
import com.androidexam.approvalmatrix.model.ApprovalMatrixWithApprover;
import com.androidexam.approvalmatrix.model.Approver;
import com.androidexam.approvalmatrix.model.ApproverDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListFragmentViewModel{
    private MutableLiveData<Boolean> buttonAddClicked = new MutableLiveData<>();
    private MutableLiveData<HashMap<String, List<ApprovalMatrixWithApprover>>> listChild = new MutableLiveData<>();

    private List<String> listGroup = new ArrayList<>();

    private ApproverDAO approverDAO;

    private Context context;

    public ListFragmentViewModel(Context context) {
        loadData();
        this.context = context;
        listGroup.add("Default");
        listGroup.add("Transfer Online");
    }

    public List<String> getListGroup() {
        return listGroup;
    }

    public LiveData<HashMap<String, List<ApprovalMatrixWithApprover>>> getListChild() {
        return listChild;
    }

    private void loadData() {

        List<ApprovalMatrixWithApprover> list = ApprovalMatrixDatabase.getInstance(context).matrixDAO().getApprovalMatrixWithApprover();
        HashMap<String, List<ApprovalMatrixWithApprover>> listChildData = new HashMap<>();

        for (ApprovalMatrixWithApprover approvalMatrixWithApprover : list) {
            String feature = approvalMatrixWithApprover.approvalMatrix.getFeature();
            List<ApprovalMatrixWithApprover> featureList;

            if (listChildData.containsKey(feature)) {
                featureList = listChildData.get(feature);
            } else {
                featureList = new ArrayList<>();
                listChildData.put(feature, featureList);
            }

            featureList.add(approvalMatrixWithApprover);
        }

        listChild.setValue(listChildData);
    }

    public void onButtonAddClicked()
    {
        buttonAddClicked.setValue(true);
    }

    public MutableLiveData<Boolean> getButtonAddClicked() {
        return buttonAddClicked;
    }
}
