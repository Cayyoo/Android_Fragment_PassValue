package com.example.pass;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pass.one.TwoActivity;
import com.example.pass.one.TwoFragment;
import com.example.pass.two.ThreeActivity;

/**
 * 示例一：OneFragment中有一个EditText，点击按钮实现将值传到TwoFragment中显示在TextView上
 */
public class OneFragment extends Fragment {
    Button btn,btn1;
    EditText edit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one,container,false);
        initView(v);
        return v;
    }

    public void initView(View v){
        btn = (Button)v.findViewById(R.id.btn);
        btn1 = (Button)v.findViewById(R.id.btn1);
        edit = (EditText)v.findViewById(R.id.edit1);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //通过调用Fragment.startActivity(intent)将值传到TwoActivity中
                Intent intent = new Intent(getActivity(),TwoActivity.class);
                intent.putExtra(TwoFragment.EXTRA_STRING, OneFragment.this.edit.getText().toString());
                startActivity(intent);
                getActivity().finish();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ThreeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

}
