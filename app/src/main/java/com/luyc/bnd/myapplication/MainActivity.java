package com.luyc.bnd.myapplication;

import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
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

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    LinearLayout activity_main;
    @InjectView(R.id.iv_phone)
    ImageView ivPhone;
    @InjectView(R.id.iv_massage)
    ImageView ivMassage;
    @InjectView(R.id.fl_mainframelayout)
    FrameLayout flMainframelayout;
    @InjectView(R.id.rb_home)
    RadioButton rbHome;
    @InjectView(R.id.rb_service)
    RadioButton rbService;
    @InjectView(R.id.rb_my)
    RadioButton rbMy;
    @InjectView(R.id.rg)
    RadioGroup rg;
    @InjectView(R.id.activity_main)
    LinearLayout activityMain;
    private MarqueeView marqueeView;
    private FragmentTransaction ft;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private HomeFragment homeFragment;
    private ServiceFragment serviceFragment;
    private MyFragment myFragment;

    private int position = 0;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        toast = Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT);

        initViewByRadioButton();

        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_mainframelayout, homeFragment);
        ft.commit();
        initView();
    }

    private void initViewByRadioButton() {
        Drawable homeDrawable = getResources().getDrawable(R.drawable.rb_home_bg_select);
        setDrawable(rbHome,homeDrawable);

        Drawable serviceDrawable = getResources().getDrawable(R.drawable.rb_service_bg_select);
        setDrawable(rbService,serviceDrawable);

        Drawable myDrawable = getResources().getDrawable(R.drawable.rb_my_bg_select);
        setDrawable(rbMy,myDrawable);
    }

    private void setDrawable(RadioButton radioButton,Drawable homeDrawable) {
        homeDrawable.setBounds(0,0,120,100);
        radioButton.setCompoundDrawables(null,homeDrawable,null,null);
    }

    private void initView() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
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

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            quitToast();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void quitToast() {
        if (toast.getView().getParent() == null) {
            toast.show();
        } else {
            finish();
        }
    }

    private void setTabSelection(int position) {
        this.position = position;//记录position
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                rbHome.setChecked(true);
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fl_mainframelayout, homeFragment, "HomeFragment");
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                rbService.setChecked(true);
                if (serviceFragment == null) {
                    serviceFragment = new ServiceFragment();
                    transaction.add(R.id.fl_mainframelayout, serviceFragment, "ServiceFragment");
                } else {
                    transaction.show(serviceFragment);
                }
                break;
            case 2:
                rbMy.setChecked(true);
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.fl_mainframelayout, myFragment, "MyFragment");
                } else {
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
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (serviceFragment != null) {
            transaction.hide(serviceFragment);
        }
        if (myFragment != null) {
            transaction.hide(myFragment);
        }
        transaction.commit();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("position", position);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        position = savedInstanceState.getInt("position");
        setTabSelection(position);
        super.onRestoreInstanceState(savedInstanceState);
    }
}
