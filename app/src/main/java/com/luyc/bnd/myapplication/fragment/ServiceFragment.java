package com.luyc.bnd.myapplication.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luyc.bnd.myapplication.R;
import com.luyc.bnd.myapplication.adapter.GridSpacingItemDecoration;
import com.luyc.bnd.myapplication.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by admin on 2017/8/10.
 */

public class ServiceFragment extends BaseFragment {
    @InjectView(R.id.id_recyclerview)
    RecyclerView idRecyclerview;
    private View view;
    private ArrayList<String> mRoomName;
    private ArrayList<String> mRoomPrice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_service, container, false);
        }
        ButterKnife.inject(this, view);

        initdata();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        idRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
        idRecyclerview.setAdapter(new RecyclerViewAdapter(getActivity(), mRoomName, mRoomPrice));
        idRecyclerview.addItemDecoration(new GridSpacingItemDecoration(2, 10, false));
        idRecyclerview.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    private void initdata() {
        mRoomName = new ArrayList<String>();
        mRoomPrice = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            mRoomName.add("海蒂大夏30" + (i + 1) + " # 客厅");
            mRoomPrice.add(1000 + i + ".0 元");
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}


