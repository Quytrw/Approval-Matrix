package com.androidexam.approvalmatrix.utilities;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexam.approvalmatrix.R;
import com.androidexam.approvalmatrix.model.ApprovalMatrixApproverCrossRef;
import com.androidexam.approvalmatrix.model.ApprovalMatrixDatabase;
import com.androidexam.approvalmatrix.model.ApprovalMatrixWithApprover;
import com.androidexam.approvalmatrix.model.Approver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApproverRecyclerViewAdapterCrUp extends RecyclerView.Adapter<ApproverRecyclerViewAdapterCrUp.ViewHolder>
{
    private List<ApprovalMatrixApproverCrossRef> arrayList = new ArrayList<>();

    private Context context;
    private LayoutInflater inflater;
    private List<Approver> allApprovers;
    private List<boolean[]> itemChecked;

    public List<Boolean> isValid;

    private int number;

    ArrayList<Integer> approverItem = new ArrayList<>();

    public ApproverRecyclerViewAdapterCrUp(Context context, int matrixId, int number) {
        arrayList = new ArrayList<>();
        isValid = new ArrayList<>();
        for(int i = 0; i < number; i++)
        {
            arrayList.add(new ApprovalMatrixApproverCrossRef(matrixId, 0, 0));
            isValid.add(false);
        }
        itemChecked = new ArrayList<>();

        this.number = number;
        this.context = context;
        inflater = LayoutInflater.from(context);
        allApprovers = ApprovalMatrixDatabase.getInstance(context).approverDAO().getAllApprovers();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_approver_cr_up, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (number != 0)
        {
            for(int i = 0; i < number; i++)
            {
                boolean[] checked = new boolean[allApprovers.size()];
                Arrays.fill(checked, false);
                itemChecked.add(checked);
            }
        }

        holder.textViewOrder.setText(String.valueOf(holder.getAdapterPosition() + 1));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] approverNames = new String[allApprovers.size()];
                boolean[] checkedItems = itemChecked.get(holder.getAdapterPosition());

                for (int i = 0; i < allApprovers.size(); i++)
                {
                    approverNames[i] = allApprovers.get(i).getName();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Select Approver");

                builder.setMultiChoiceItems(approverNames, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked)
                        {
                            if(!approverItem.contains(which))
                            {
                                approverItem.add(which);
                                checkedItems[which] = true;
                            }
                            else if(approverItem.contains(which))
                            {
                                approverItem.remove(which);
                                checkedItems[which] = false;
                            }
                        }
                        else
                        {
                            checkedItems[which] = false;
                        }
                    }
                });

                builder.setCancelable(false);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int count = 0;
                        for (boolean checked : checkedItems) {
                            if (checked) {
                                count++;
                            }
                        }
                        String item = "";
                        for (int i = 0; i < checkedItems.length; i++) {
                            if (checkedItems[i]) {
                                item += approverNames[i];
                                arrayList.get(holder.getAdapterPosition()).setApproverId(i + 1);
                                arrayList.get(holder.getAdapterPosition()).setOrderIndex(holder.getAdapterPosition());
                                count--;
                                if (count > 0) {
                                    item += ", ";
                                }
                            }
                        }
                        if(count == 0)
                        {
                            isValid.set(holder.getAdapterPosition(), false);
                        }
                        else
                        {
                            isValid.set(holder.getAdapterPosition(), true);
                        }
                        holder.textViewApprover.setText(item);

                        itemChecked.set(holder.getAdapterPosition(), checkedItems);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        holder.textViewApprover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView titleView = new TextView(context);
                titleView.setText("Approver " + holder.textViewOrder.getText());
                titleView.setGravity(Gravity.CENTER);
                titleView.setTextSize(20F);
                titleView.setTextColor(ContextCompat.getColor(context, R.color.primary_color));

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(holder.textViewApprover.getText());
                builder.setCustomTitle(titleView);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private Button button;
        private TextView textViewOrder;
        private TextView textViewApprover;

        public ViewHolder(View view)
        {
            super(view);
            button = view.findViewById(R.id.btn_choose);
            textViewOrder = view.findViewById(R.id.tv_orderIndex);
            textViewApprover = view.findViewById(R.id.tv_approvers);
        }
    }

    public boolean getIsValid()
    {
        boolean valid = true;
        for (int i = 0; i < number; i++)
        {
            if (isValid.get(i))
            {
                continue;
            }
            else
            {
                valid = false;
                break;
            }
        }
        return valid;
    }

    public List<ApprovalMatrixApproverCrossRef> getArrayList() {
        return arrayList;
    }
}
