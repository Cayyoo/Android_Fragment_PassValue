package com.example.pass.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.pass.R;

/***
 * 适配器设计模式
 */
public abstract class BaseActivity extends FragmentActivity {
    protected  abstract Fragment createFragment();

    protected int getLayoutResId(){
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContain);

        if(fragment  == null){
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragmentContain,fragment).commit();
        }

    }

}
