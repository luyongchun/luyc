package com.luyc.bnd.myapplication.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by admin on 2017/8/14.
 */

public class MyImageViewAdapter extends PagerAdapter {

    private final ArrayList<ImageView> list;

    public  MyImageViewAdapter(ArrayList<ImageView> list){
        this.list=list;

    }

    @Override
    public int getCount() {
        //设置成最大值，使用户看不到边界
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //不能在这里调用removeView
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        //对viewPager页号求模去除view列表中要显示的项

        position %=list.size();
        if (position<0){
            position = list.size()+position;
        }
        ImageView view = list.get(position);
        //如果view已经在之前添加到了一个父组件，则必须先remove，否则会报IllegalStateException
        ViewParent parent = view.getParent();
        if (parent!=null){
            ViewGroup parent1 = (ViewGroup) parent;
            parent1.removeView(view);
        }
        container.addView(view);

        return view;
    }


}
