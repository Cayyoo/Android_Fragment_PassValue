package com.example.pass.one;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pass.R;

/**
 * 示例二：在TwoFragment加入一个按钮打开一个MyDialogFragment，传值进去，dialog销毁后返回值。
 */
public class TwoFragment extends Fragment {
    public static final String EXTRA_STRING="DATA";

    TextView txt;
    Button btn,btnBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_two,container,false);

        txt = (TextView)v.findViewById(R.id.txt2);
        btn = (Button)v.findViewById(R.id.btn2);
        btnBack = (Button)v.findViewById(R.id.back);

        String str = getArguments().getString(EXTRA_STRING);//获取传递数据

        txt.setText(str);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * 最主要的是在把TwoFragment设为是myDialog的目标Fragment,使两者建立联系，
                 * 这样目标Fragment就交给了FragmentManage管理，方便之后获取目标Fragment。
                 */
                FragmentManager fm = getActivity().getSupportFragmentManager();
                MyDialogFragment dialogFragment =
                        (MyDialogFragment) MyDialogFragment.newInstance(TwoFragment.this.txt.getText().toString());
                //设置传值到SecondFragment，0是requestCode
                dialogFragment.setTargetFragment(TwoFragment.this, 0);
                //"Data"是Tag
                dialogFragment.show(fm, "Data");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //退出当前fragment
                //getFragmentManager().popBackStack();
                getActivity().finish();
            }
        });

        return v;
    }

    /*
     * 通过Fragment.setArguments(bundle)传值
     */
    public static Fragment newInstance(String s){
        Bundle args = new Bundle();
        args.putString(EXTRA_STRING, s);
        TwoFragment fragment = new TwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /*
     * 通过回调，接收MyDialogFragment回传的值
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(resultCode != Activity.RESULT_OK){
           return;
       }else{
           String str = data.getStringExtra(MyDialogFragment.EXTRADATA);
           txt.setText(str);
       }
    }

}
