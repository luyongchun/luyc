package com.luyc.bnd.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luyc.bnd.myapplication.R;

import java.util.ArrayList;

/**
 * Created by admin on 2017/8/12.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{

    private Context context;
    private ArrayList<String> roomName;
    private ArrayList<String> roomPrice;
    private ArrayList<ImageView> roomImage;
    private View view;


    public RecyclerViewAdapter(Context context, ArrayList<String> roomName,
                               ArrayList<String> roomPrice){
        this.context = context;
        this.roomName = roomName;
        this.roomPrice = roomPrice;
    }


    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(context).inflate(R.layout.service_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvRoomName.setText(roomName.get(position));
        holder.tvRoomPrice.setText(roomPrice.get(position));

    }

    @Override
    public int getItemCount() {
        if (roomName !=null){
            return roomName.size();
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        private final ImageView ivSerciceBg;
        private final TextView tvRoomName;
        private final TextView tvRoomPrice;

        public MyViewHolder(View view)
        {
            super(view);
            ivSerciceBg = ((ImageView) view.findViewById(R.id.iv_service_bg));
            tvRoomName = ((TextView) view.findViewById(R.id.tv_room_name));
            tvRoomPrice = ((TextView) view.findViewById(R.id.tv_room_price));

        }
    }

}
