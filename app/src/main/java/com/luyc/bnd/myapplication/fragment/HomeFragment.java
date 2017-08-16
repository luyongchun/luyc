package com.luyc.bnd.myapplication.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luyc.bnd.myapplication.R;
import com.luyc.bnd.myapplication.adapter.GridViewAdapter;
import com.luyc.bnd.myapplication.adapter.MyImageViewAdapter;
import com.luyc.bnd.myapplication.view.MyGridViews;
import com.sunsky.marqueeview.MarqueeView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/8.
 */

public class HomeFragment extends BaseFragment {

    private MarqueeView marqueeView;
    private View view;
    ArrayList<String> data = new ArrayList<>();
    ArrayList<View> views = new ArrayList<>();
    private ViewPager vp;
    private List<String> roomNumber =new ArrayList<>();
    private List<String> rentSituatoin =new ArrayList<>();
    private ArrayList<ImageView> list =new ArrayList<>();
    private MyGridViews gridView;
    private ImageHandler handler = new ImageHandler(new WeakReference<HomeFragment>(HomeFragment.this));
    private static boolean isFrist=false;
    private int pageCount;
    private ImageView imageView;
    private ArrayList<ImageView> dotsList;
//    private LinearLayout mLinearLayout;
//    private View vRepoint;
    private int diatance;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null){
            view = inflater.inflate(R.layout.fragment_home,container,false);
        }
        isFrist =true;
        initdata();
        initView();
        setGridViewClick();
        setImageView();
        setViewPager();

        return view;

    }



    private void setViewPager() {

        vp.setAdapter(new MyImageViewAdapter(list));


        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                handler.sendMessage(Message.obtain(handler,
                        ImageHandler.MSG_PAGE_CHANGED,
                        position,0));


            }
            //复写该方法实现轮播效果的暂停和恢复
            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state){
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
                        break;
                    default:
                        break;
                }
            }

        });
        vp.setCurrentItem(Integer.MAX_VALUE/2);

        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
    }

    private void setImageView() {
        ImageView view1 = new ImageView(getContext());
        view1.setBackgroundResource(R.mipmap.banner1);
        list.add(view1);
        ImageView view2 = new ImageView(getContext());
        view2.setBackgroundResource(R.mipmap.lock);
        list.add(view2);
        ImageView view3 = new ImageView(getContext());
        view3.setBackgroundResource(R.mipmap.open_door);
        list.add(view3);
    }

    private void setGridViewClick() {
        gridView.setAdapter(new GridViewAdapter(getActivity(),roomNumber,rentSituatoin));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"当前房号为："+roomNumber.get(i).toString()
                        +",出租情况："+rentSituatoin.get(i).toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    //初始化控件
    private void initView() {
        gridView = ((MyGridViews) view.findViewById(R.id.gridView));
        marqueeView = ((MarqueeView) view.findViewById(R.id.marqueeView));
        vp = ((ViewPager) view.findViewById(R.id.vp));

        setView();
        marqueeView.setViews(views);
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int i, View view) {
                Toast.makeText(getActivity(), data.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setView() {
        views.clear();
        for (int i = 0; i < data.size(); i++) {
            final int position = i;
            //设置滚动的单个布局
            LayoutInflater moreView = LayoutInflater.from(getContext());
            View view = moreView.inflate(R.layout.notify, null);
            TextView tvMssage = (TextView) view.findViewById(R.id.tv_notify);
            tvMssage.setText(data.get(i).toString());
            ((LinearLayout) view.findViewById(R.id.ll)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"通知："+data.get(position).toString(),Toast.LENGTH_LONG).show();
                }
            });
            views.add(view);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        marqueeView.startFlipping();

    }

    @Override
    public void onPause() {
        super.onPause();
        marqueeView.stopFlipping();
    }

    private int[] getImages(){
        return new int[]{R.mipmap.safety_monitoring,R.mipmap.banner1,R.mipmap.massage,R.mipmap.lock};
    }


     //初始化数据
    private void initdata() {
        for (int i = 0; i < 40; i++) {
            roomNumber.add("房间"+(i+1));
            rentSituatoin.add("未出租");
        }
        data = new ArrayList<>();

        data.add("家人给2岁孩子喝这个，孩子智力倒退10岁!!!");

        data.add("iPhone8最感人变化成真，必须买买买买!!!!");

        data.add("简直是白菜价！日本玩家33万甩卖15万张游戏王卡");

        data.add("iPhone7价格曝光了！看完感觉我的腰子有点疼...");

        data.add("主人内疚逃命时没带够，回废墟狂挖30小时！");

        data.add("竟不是小米乐视！看水抢了骁龙821首发了！！！");

    }
    private static class ImageHandler extends Handler{
        //请求更新显示的view
        protected static final int MSG_UPDATE_IMAGE = 1;
        //请求暂停轮播
        protected static final int MSG_KEEP_SILENT = 2;
        //请求恢复轮播
        protected static final int MSG_PAGE_SILENT =3;
        /*
        记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
    例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
    则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
         */
        protected  static final int MSG_PAGE_CHANGED=4;

        //轮播间隔时间
        protected static final long MSG_DELAY = 3000;
        //使用弱引用避免handler泄露
        private WeakReference<HomeFragment> weakReference;
        private int currentItem =0;

        protected ImageHandler(WeakReference<HomeFragment> wk){
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HomeFragment fragment = weakReference.get();
            if (fragment ==null){
                return;//活动已经回收，无需再处理ui
            }
            //检查消息队列并移除未发送的消息，
            // 这是为了避免在复杂环境下消息出现重复等为题
            if (fragment.handler.hasMessages(MSG_UPDATE_IMAGE) && !isFrist){
                fragment.handler.removeMessages(MSG_UPDATE_IMAGE);
                isFrist = false;
            }
            switch (msg.what){
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    fragment.vp.setCurrentItem(currentItem);
                    fragment.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE,MSG_DELAY);
                    isFrist =false;
                    break;
                case MSG_KEEP_SILENT:
                    //只要不发送消息就暂停
                    break;
                case MSG_PAGE_SILENT:
                    fragment.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE,MSG_DELAY);
                    isFrist =false;
                    break;
                case MSG_PAGE_CHANGED:
                    currentItem =msg.arg1;
                    isFrist =false;
                    break;
                default:
                    break;
            }

        }
    }
}
