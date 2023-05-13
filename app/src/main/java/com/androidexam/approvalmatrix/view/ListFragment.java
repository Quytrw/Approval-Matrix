package com.androidexam.approvalmatrix.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidexam.approvalmatrix.R;
import com.androidexam.approvalmatrix.databinding.FragmentListBinding;
import com.androidexam.approvalmatrix.viewmodel.ListFragmentViewModel;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;
    private ListFragmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.fragment_list, container, false);

        viewModel = new ListFragmentViewModel();
        binding.setListFragmentViewModel(viewModel);

        viewModel.getButtonAddClicked().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean)
                {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fr_main, new CreateFragment())
                            .addToBackStack(null)
                            .commit();
                    viewModel.getButtonAddClicked().setValue(false);
                }
            }
        });

        View viewRoot = binding.getRoot();
        return viewRoot;
    }


}