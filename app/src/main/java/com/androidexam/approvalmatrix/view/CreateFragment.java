package com.androidexam.approvalmatrix.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.androidexam.approvalmatrix.R;
import com.androidexam.approvalmatrix.databinding.FragmentCreateBinding;
import com.androidexam.approvalmatrix.model.ApprovalMatrix;
import com.androidexam.approvalmatrix.model.ApprovalMatrixApproverCrossRef;
import com.androidexam.approvalmatrix.model.ApprovalMatrixDatabase;
import com.androidexam.approvalmatrix.utilities.ApproverRecyclerViewAdapterCrUp;
import com.androidexam.approvalmatrix.utilities.MySpinnerAdapter;
import com.androidexam.approvalmatrix.viewmodel.CreateFragmentViewModel;

import java.util.Arrays;
import java.util.List;

public class CreateFragment extends Fragment {

    private FragmentCreateBinding binding;
    private CreateFragmentViewModel viewModel;

    private MySpinnerAdapter mySpinnerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.fragment_create, container, false);

        viewModel = new CreateFragmentViewModel(getContext());
        binding.setCreateFragmentViewModel(viewModel);

        binding.rvApprover.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvApprover.setAdapter(viewModel.getAdapter());

        setHasOptionsMenu(true);

        View viewRoot = binding.getRoot();
        return viewRoot;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<String> items = Arrays.asList(viewModel.getSuggestions());

        mySpinnerAdapter = new MySpinnerAdapter(getContext(), items);
        binding.spFeature.setAdapter(mySpinnerAdapter);

        binding.spFeature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                viewModel.setSelectedItem(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        binding.etNumberAprrover.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    int count = 0;
                    try {
                        count = Integer.parseInt(binding.etNumberAprrover.getText().toString());
                        viewModel.setItemCount(count);
                    } catch (NumberFormatException e) {
                    }
                    viewModel.getItemCountLiveData().observe(getViewLifecycleOwner(), itemCount -> {
                        binding.rvApprover.setAdapter(viewModel.getAdapter());
                    });
                }
            }
        });



        viewModel.getButtonAddClicked().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                String name = String.valueOf(binding.etName.getText());
                String feature = (String) mySpinnerAdapter.getItem(binding.spFeature.getSelectedItemPosition());
                String min = String.valueOf(binding.etMinRange.getText());
                String max = String.valueOf(binding.etMaxRange.getText());
                String number = String.valueOf(binding.etNumberAprrover.getText());
                if(aBoolean)
                {
                    Log.d("DEBUG", "name" + String.valueOf(name.equals("")));
                    Log.d("DEBUG", "feature" + String.valueOf(feature.equals("")));
                    Log.d("DEBUG", "min" + String.valueOf(min.equals("")));
                    Log.d("DEBUG", "max" + String.valueOf(max.equals("")));
                    Log.d("DEBUG", "number" + String.valueOf(number.equals("")));
                    Log.d("DEBUG", "valid" + String.valueOf(viewModel.getAdapter().getIsValid()));
                    Log.d("DEBUG", "ss" + String.valueOf((Long.parseLong(min) < Long.parseLong(max))));
                    Log.d("DEBUG", "list" + String.valueOf(viewModel.getAdapter().getArrayList() != null));

                    if (!name.equals("") && !feature.equals("")
                            && !min.equals("") && !max.equals("")
                            && !number.equals("")  && !number.equals("0")
                            && !viewModel.getAdapter().getIsValid()
                            && (Long.parseLong(min) < Long.parseLong(max)) && viewModel.getAdapter().getArrayList() != null)
                    {
                        Log.d("DEBUG", min);
                        ApprovalMatrix approvalMatrix = new ApprovalMatrix(viewModel.getId(), name, feature, min, max, number);
                        List<ApprovalMatrixApproverCrossRef> list = viewModel.getAdapter().getArrayList();
                        ApprovalMatrixDatabase.getInstance(getContext()).matrixDAO().insertApprovalMatrix(approvalMatrix);
                        for (ApprovalMatrixApproverCrossRef i : list)
                        {
                            ApprovalMatrixDatabase.getInstance(getContext()).approvalMatrixApproverCrossRefDAO().insertApprovalMatrixApproverCrossRef(i);
                        }
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();
                        onBack();
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    public void onBack()
    {
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
        }

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fr_main, new ListFragment())
                .addToBackStack(null)
                .commit();
    }
}