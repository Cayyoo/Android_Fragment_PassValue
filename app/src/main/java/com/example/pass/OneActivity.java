package com.example.pass;

import android.support.v4.app.Fragment;

import com.example.pass.base.BaseActivity;


/**
 * Fragment的传值
 *
 * 参考：https://www.kancloud.cn/digest/android-lxj/138593
 *
 * Fragment，碎片，是Android 3.0之后加入的一个非常重要的概念。
 * 每个Fragment都有相应的Activity对它进行托管。
 * 正常的托管模式是Activity知道Fragment的具体情况，但Fragment不能也不应该知道Activity中的具体情况。
 * 一个Activity中可以有多个Fragment，这很自然的给大屏幕的适配提供了很便捷的方案。
 *
 * Fragment之间不能直接通信，必须通过Activity来完成。
 *
 * 本例中：
 *     OneFragment在OneActivity中。
 *     TwoFragment、MyDialogFragment在TwoActivity中。TwoFragment是MyDialogFragment的父Fragment
 *     ThreeFragment、FourFragment在ThreeActivity中，且处于同一平行界面。
 *
 *
 * 本文总结了Fragment在不同情况下的传值方法：
 *
 * 一、不同Activity下的Fragment的传值。
 *    （Fragment的传值就与Acitvity一样简单）
 *   1、通过调用Fragment.startActivity(intent)将值传到TwoActivity中
 *   2、直接通过getIntent获取值，但这样做就破坏的Fragment的独立性。
 *      因为此时TwoFragment总需要被TwoActivity托管，而不能用于其他Activity中，否则就可能因获取不到intent而报错。
 *   3、每个fragment都有一个Bundle对象，可把传过来的值存到bundle中。bundle可以添加argument(key-value对象)，
 *      在给fragment添加bundle时要注意，Fragment.setArguments(bundle)需要在fragment创建后，添加到activity前完成。
 *      然后通过acitivity在建立fragment时传入值来实现fragment的独立性。
 *   即，Intent-getIntent-setArguments
 *
 * 二、相同Acitvity托管下不同Fragment的传值。
 *    （父、子Fragment）
 *   1、使用setTargetFragment()把某个Fragment设为另一个Fragment的目标Fragment,即父Fragment,使两者建立联系，
 *      这样目标Fragment就交给了FragmentManager管理，方便之后获取目标Fragment。
 *      这步操作在父Fragment中完成，如给一个点击事件。
 *   2、在子Fragment中定义一个方法，调用父Fragment的回调方法
 *      getTargetFragment().onActivityResult()将修改后的值返回到父Fragment中。
 *   3、在父Fragment中添加回调方法onActivityResult()处理返回值。
 *
 * 三、同一界面不同Fragment传值并实时变化的情况。
 *     可使用接口回调实现Fragment之间的传值。
 *
 * 四、使用EventBus等第三方框架也可以实现Fragment之间的传值（本Demo暂不使用EventBus）
 */

/**
 * Fragment传值总结：
 * 1、构造方法(不推荐)
 * 2、使用startActivity(Intent intent)传值
 * 3、使用setArguments()传参
 * 4、使用onActivityResult()回调
 * 5、使用接口回调
 * 6、第三方框架，如EventBus
 *
 * Fragment返回上一个Fragment如果直接返回不需要传参数使用getFragmentManager().popBackStack();
 */

/**
 * 不推荐使用构造方法传参：
 * Activity重新创建时，会重新构建它所管理的Fragment，原先的Fragment的字段值将会全部丢失，
 * 但是通过Fragment.setArguments(Bundle bundle)方法设置的bundle会保留下来。
 * 所以尽量使用Fragment.setArguments(Bundle bundle)方式来传递参数
 */
public class OneActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {
        return new OneFragment();
    }

}
