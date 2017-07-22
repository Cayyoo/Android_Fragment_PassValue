package com.example.pass.two;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.pass.R;

/**
 * 示例三：一个界面同时有两个Fragment，左边的Fragment里的EditText变化会引起右边的实时变化。
 *
 * 最简单的做法就是在EditText的Fragment中监听edit的变化，然后直接创建FragmentManger，
 * 获得另一个Fragment并动态的改变其中的内容。
 * 这是最直接最简单的方法，但是还是那句话，Fragment的独立性很重要，
 * 如果这样做就要求EditText的Fragment知道TextView所在的Fragment的相关细节。
 *
 * 故选用另一种最佳方法：回调。
 *
 * ThreeActivity分两次加载不同的Fragment(ThreeFragment、FourFragment)。并实现ThreeFragment中的回调接口。
 * 在接口中获得FourFragment，调用其update()方法
 *
 * FragmentA启动FragmentB,做一些选择操作后，返回FragmentA，需要把FragmentB里面选择的数据传回来：
 * 1、在FragmentA中定义通信接口，通过该接口向Activity发送数据。
 * 2. 在Activity中实现该接口，并通过该接口向FragmentB传递数据。
 * 3. FragmentB接收到数据并处理。
 */
public class ThreeActivity extends FragmentActivity implements ThreeFragment.Callbacks {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_three);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment1 = fm.findFragmentById(R.id.frame1);
        Fragment fragment2 = fm.findFragmentById(R.id.frame2);

        if(fragment1 == null){
            fragment1 = new ThreeFragment();
            fm.beginTransaction().add(R.id.frame1,fragment1).commit();
        }

        if(fragment2 == null){
            fragment2 = new FourFragment();
            fm.beginTransaction().add(R.id.frame2,fragment2).commit();
        }
    }

    @Override
    public void onChangeText(String s) {
        FragmentManager fm = getSupportFragmentManager();
        FourFragment fourFragment= (FourFragment)fm.findFragmentById(R.id.frame2);
        fourFragment.update(s);
    }

}
