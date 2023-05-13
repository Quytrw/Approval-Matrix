package com.androidexam.approvalmatrix.utilities;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexam.approvalmatrix.R;
import com.androidexam.approvalmatrix.model.ApprovalMatrixApproverCrossRef;
import com.androidexam.approvalmatrix.model.ApprovalMatrixDatabase;
import com.androidexam.approvalmatrix.model.ApprovalMatrixWithApprover;
import com.androidexam.approvalmatrix.model.Approver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listGroup;

    private HashMap<String, List<ApprovalMatrixWithApprover>> listChild;

    public MyExpandableListAdapter(Context context, List<String> listGroup, HashMap<String, List<ApprovalMatrixWithApprover>> listChild) {
        this.context = context;
        this.listGroup = listGroup;
        this.listChild = listChild;
    }

    public void setListChild(HashMap<String, List<ApprovalMatrixWithApprover>> listChild) {
        this.listChild = listChild;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listChild.get(this.listGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listChild.get(this.listGroup.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_group, parent, false);

        String headerTitle = (String) getGroup(groupPosition);

        TextView header1 = (TextView) convertView.findViewById(R.id.tv_feature1);
        header1.setText(headerTitle);

        TextView header2 = (TextView) convertView.findViewById(R.id.tv_feature2);
        header2.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_matrix, parent, false);

        TextView tvMin = (TextView) convertView.findViewById(R.id.tv_miniRange);
        TextView tvMax = (TextView) convertView.findViewById(R.id.tv_maxRange);
        TextView tvNumber = (TextView) convertView.findViewById(R.id.tv_number);

        ApprovalMatrixWithApprover matrixWithApprover = (ApprovalMatrixWithApprover) getChild(groupPosition, childPosition);

        tvMin.setText(String.valueOf(matrixWithApprover.approvalMatrix.getMinimumRange()));
        tvMax.setText(String.valueOf(matrixWithApprover.approvalMatrix.getMaximumRange()));
        tvNumber.setText(String.valueOf(matrixWithApprover.approvalMatrix.getNumberOfApproval()));

        RecyclerView recyclerView = convertView.findViewById(R.id.rv_approver);
        LinearLayoutManager layoutManager = new LinearLayoutManager(parent.getContext());

        List<ApprovalMatrixApproverCrossRef> approverCrossRefList = ApprovalMatrixDatabase.getInstance(context).approvalMatrixApproverCrossRefDAO().getApprovalMatrixApproverCrossRefListByMatrixId(matrixWithApprover.approvalMatrix.getMatrixId());
        int number = Integer.parseInt(matrixWithApprover.approvalMatrix.getNumberOfApproval());


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerViewAdapterChild(handleData(approverCrossRefList, number)));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public List<List<String>> handleData(List<ApprovalMatrixApproverCrossRef> approverCrossRefList, int number) {
        List<Approver> approverList = ApprovalMatrixDatabase.getInstance(context).approverDAO().getAllApprovers();
        HashMap<Integer, String> approverNameMap = new HashMap<>();

        for (Approver approver : approverList) {
            approverNameMap.put(approver.getApproverId(), approver.getName());
        }

        List<List<String>> lists = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            List<String> approverNames = new ArrayList<>();
            for (ApprovalMatrixApproverCrossRef crossRef : approverCrossRefList) {
                if (crossRef.getOrderIndex() == i) {
                    int approverId = crossRef.getApproverId();
                    approverNames.add(approverNameMap.get(approverId));
                }
            }
            lists.add(approverNames);
        }
        return lists;
    }

}
