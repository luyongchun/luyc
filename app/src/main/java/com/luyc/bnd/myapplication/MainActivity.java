package com.luyc.bnd.myapplication;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.luyc.bnd.myapplication.activity.BaseActivity;
import com.luyc.bnd.myapplication.fragment.HomeFragment;
import com.luyc.bnd.myapplication.fragment.MyFragment;
import com.luyc.bnd.myapplication.fragment.ServiceFragment;
import com.sunsky.marqueeview.MarqueeView;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    LinearLayout activity_main;
    private MarqueeView marqueeView;
    private FragmentTransaction ft;
    private ArrayList<android.support.v4.app.Fragment> fragments = new ArrayList<>();
    private HomeFragment homeFragment;
    private ServiceFragment serviceFragment;
    private MyFragment myFragment;
    private ViewPager vp_main;
    private FrameLayout fl;
    private RadioGroup rg;
    private RadioButton rb_home;
    private RadioButton rb_service;
    private RadioButton rb_my;
    private int position=0;
    private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fl = ((FrameLayout) findViewById(R.id.fl_mainframelayout));
        rg = ((RadioGroup) findViewById(R.id.rg));
        rb_home=((RadioButton) findViewById(R.id.rb_home));
        rb_service=((RadioButton) findViewById(R.id.rb_service));
        rb_my= ((RadioButton) findViewById(R.id.rb_my));
        toast = Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT);

        if (homeFragment ==null){
            homeFragment =new HomeFragment();
        }
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_mainframelayout,homeFragment);
        ft.commit();
        initView();
    }

    private void initView() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home:
                        setTabSelection(0);
                        break;
                    case R.id.rb_service:
                        setTabSelection(1);
                        break;
                    case R.id.rb_my:
                        setTabSelection(2);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode ==KeyEvent.KEYCODE_BACK){
            quitToast();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void quitToast() {
        if (toast.getView().getParent() ==null){
            toast.show();
        }else {
            finish();
        }
    }

    private void setTabSelection(int position) {
        this.position=position;//记录position
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position){
            case 0:
                rb_home.setChecked(true);
                if (homeFragment ==null){
                    homeFragment =new HomeFragment();
                    transaction.add(R.id.fl_mainframelayout,homeFragment,"HomeFragment");
                }else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                rb_service.setChecked(true);
                if (serviceFragment ==null){
                    serviceFragment =new ServiceFragment();
                    transaction.add(R.id.fl_mainframelayout,serviceFragment,"ServiceFragment");
                }else {
                    transaction.show(serviceFragment);
                }
                break;
            case 2:
                rb_my.setChecked(true);
                if (myFragment ==null){
                    myFragment = new MyFragment();
                    transaction.add(R.id.fl_mainframelayout,myFragment,"MyFragment");
                }else {
                    transaction.show(myFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();


    }
    //隐藏fragment
    private void hideFragment(android.support.v4.app.FragmentTransaction transaction) {
        if (homeFragment!=null){
            transaction.hide(homeFragment);
        }
        if (serviceFragment !=null){
            transaction.hide(serviceFragment);
        }
        if (myFragment !=null){
            transaction.hide(myFragment);
        }
        transaction.commit();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("position",position);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        position = savedInstanceState.getInt("position");
        setTabSelection(position);
        super.onRestoreInstanceState(savedInstanceState);
    }
}
