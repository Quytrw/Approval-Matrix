package com.androidexam.approvalmatrix.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.androidexam.approvalmatrix.R;
import com.androidexam.approvalmatrix.databinding.ActivityMainBinding;
import com.androidexam.approvalmatrix.model.ApprovalMatrixDatabase;
import com.androidexam.approvalmatrix.model.Approver;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
        {
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        }

        getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fr_main, new ListFragment())
                        .commit();

        List<Approver> approverList = new ArrayList<>();
        approverList.add(new Approver(1, "GROUPMG1"));
        approverList.add(new Approver(2, "GROUPMG2"));
        approverList.add(new Approver(3, "GROUPMG3"));
        approverList.add(new Approver(4, "GROUPFM1"));
        approverList.add(new Approver(5, "GROUPFM2"));
        approverList.add(new Approver(6, "GROUPFM3"));
        approverList.add(new Approver(7, "GROUPACROSS"));
        approverList.add(new Approver(8, "FD1"));
        approverList.add(new Approver(9, "FD2"));
        approverList.add(new Approver(10, "CFO"));
        approverList.add(new Approver(11, "CEO"));
        approverList.add(new Approver(12, "CHAIRMAN"));

        for(Approver i : approverList)
        {
            ApprovalMatrixDatabase.getInstance(this).approverDAO().insertApprover(i);
        }


    }
}