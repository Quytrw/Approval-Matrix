package com.androidexam.approvalmatrix.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MySpinnerAdapter extends BaseAdapter {
    private List<String> mData;
    private LayoutInflater mInflater;

    public MySpinnerAdapter(Context context, List<String> data) {
        mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {

        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        } else {
            view = convertView;
        }
        TextView textView = view.findViewById(android.R.id.text1);
        String data = mData.get(position);

        if (position == 0)
        {
            textView.setText("");
            textView.setHint(data);
        }
        else {
            textView.setText(data);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        } else {
            view = convertView;
        }
        TextView textView = view.findViewById(android.R.id.text1);

        String data = mData.get(position);

        if (position == 0)
        {
            textView.setText("");
            textView.setHint(data);
        }
        else {
            textView.setText(data);
        }
        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return position != 0;
    }
}


