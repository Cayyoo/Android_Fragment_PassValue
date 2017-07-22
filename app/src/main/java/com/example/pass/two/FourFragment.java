package com.example.pass.two;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.pass.R;


public class FourFragment extends Fragment {
    EditText edit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_five,container,false);
        edit = (EditText)v.findViewById(R.id.edit);
        return v;
    }

    public void update(String str){
        edit.setText(str);
    }

}
