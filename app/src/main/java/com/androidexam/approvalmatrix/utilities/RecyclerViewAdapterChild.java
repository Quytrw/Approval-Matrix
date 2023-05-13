package com.androidexam.approvalmatrix.utilities;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexam.approvalmatrix.R;
import com.androidexam.approvalmatrix.model.ApprovalMatrixWithApprover;
import com.androidexam.approvalmatrix.model.Approver;

import java.util.HashMap;
import java.util.List;

public class RecyclerViewAdapterChild extends RecyclerView.Adapter<RecyclerViewAdapterChild.ViewHolder> {

    private List<List<String>> data;

    public RecyclerViewAdapterChild(List<List<String>> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_approver, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<String> item = data.get(position);
        Log.d("DEBUG", "item" + String.valueOf(item.size()));
        holder.tvIndex.setText(String.valueOf(position + 1));
        String approver = "";
        for (int i = 0; i < item.size(); i++)
        {
            approver += item.get(i) + ",";
        }

        if (!approver.isEmpty()) {
            approver = approver.substring(0, approver.length() - 1);
        }

        holder.tvApproverList.setText(approver);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvIndex;
        TextView tvApproverList;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIndex = itemView.findViewById(R.id.tv_index);
            tvApproverList = itemView.findViewById(R.id.tv_approverl);
        }
    }
}
