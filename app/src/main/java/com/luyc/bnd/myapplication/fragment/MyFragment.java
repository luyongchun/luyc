package com.luyc.bnd.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.luyc.bnd.myapplication.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by admin on 2017/8/10.
 */

public class MyFragment extends BaseFragment {

    @InjectView(R.id.imageView2)
    ImageView imageView2;
    @InjectView(R.id.radioButton)
    RadioButton radioButton;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_my, container, false);
        }
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
