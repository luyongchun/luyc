package com.luyc.bnd.myapplication.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;
import java.util.Objects;

/**
 * Created by admin on 2017/8/9.
 */

public class MyViewPagerAdapter extends PagerAdapter {

    private List<ImageView> mlist;

    public MyViewPagerAdapter(List<ImageView> mlist){
        this.mlist =mlist;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mlist.get(position));
        return mlist.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(mlist.get(position));

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE/2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
