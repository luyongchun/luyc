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

/**
 * Created by admin on 2017/8/10.
 */

public class ServiceFragment extends BaseFragment {
    private View view;
    private ArrayList<String> mRoomName;
    private ArrayList<String> mRoomPrice;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view==null){
            view = inflater.inflate(R.layout.fragment_service,container,false);
        }
        recyclerView = ((RecyclerView) view.findViewById(R.id.id_recyclerview));
        initdata();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(new RecyclerViewAdapter(getActivity(),mRoomName,mRoomPrice));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,10,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    private void initdata() {
        mRoomName =new ArrayList<String>();
        mRoomPrice =new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            mRoomName.add("海蒂大夏30"+(i+1)+" # 客厅");
            mRoomPrice.add(1000+i+".0 元");
        }

    }


}


