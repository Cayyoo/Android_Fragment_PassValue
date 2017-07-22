package com.example.pass.one;

import android.support.v4.app.Fragment;
import com.example.pass.base.BaseActivity;


public class TwoActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {
       String str = getIntent().getStringExtra(TwoFragment.EXTRA_STRING);
        return TwoFragment.newInstance(str);
    }

}
