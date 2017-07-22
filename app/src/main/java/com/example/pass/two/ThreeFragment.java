package com.example.pass.two;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.pass.R;

/**
 * 在ThreeFragment中定义了回调接口，回调接口定义了fragment委托给托管activity处理的工作。
 * 任何托管这个fragment都要实现这个接口。
 * 在onAttach方法中，将activity强制转换成callbacks并赋值给Callbacks变量。
 * 这样在onTextChanged中调用接口的onChangeText()相当于在Activity中调用。
 */
public class ThreeFragment extends Fragment{
    EditText edit;

    private Callbacks mCallbacks;

    interface Callbacks{
        void onChangeText(String s);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_four,container,false);
        initView(v);
        return v;
    }

    public void initView(View v){
        edit = (EditText)v.findViewById(R.id.edittext);
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = edit.getText().toString();
                mCallbacks.onChangeText(str);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks)activity;
    }

}
