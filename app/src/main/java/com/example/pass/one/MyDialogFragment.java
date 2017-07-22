package com.example.pass.one;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.example.pass.R;


public class MyDialogFragment extends DialogFragment {
    public static final String EXTRADATA = "DIALOG";

    EditText dialogEdit;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_dialog,null);

        dialogEdit= (EditText)v.findViewById(R.id.edit);
        dialogEdit.setText(getArguments().getString(EXTRADATA));

        return new AlertDialog.Builder(getActivity())
                .setTitle("")
                .setView(v)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);//传值
                    }
                })
                .create();
    }

    public static Fragment newInstance(String s){
        Bundle args = new Bundle();
        args.putString(EXTRADATA,s);
        MyDialogFragment fragment = new MyDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /*
     * 1、在sendResult()中调用父Fragment的回调方法将修改后的值返回到父Fragment中
     *    TwoFragment是MyDialogFragment的父Fragment
     *
     * 2、然后在TwoFragment中添加回调方法处理返回值
     */
    public void sendResult(int s){
        if(getTargetFragment() == null){
            return;
        }else{
            Intent intent = new Intent();
            intent.putExtra(EXTRADATA, dialogEdit.getText().toString());
            getTargetFragment().onActivityResult(getTargetRequestCode(), s, intent);
        }
    }

}
