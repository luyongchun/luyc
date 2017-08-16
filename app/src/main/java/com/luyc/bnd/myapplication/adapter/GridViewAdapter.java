package com.luyc.bnd.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.luyc.bnd.myapplication.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by admin on 2017/8/11.
 */

public class GridViewAdapter extends BaseAdapter {
    Context context;
    List<String> roorNumber;
    List<String> rentSituation;
    private TextView tvRoomNum;
    private TextView tvRentSituation;

    public GridViewAdapter(Context context, List<String> roorNumber, List<String> rentSituation){
        this.context = context;
        this.roorNumber = roorNumber;
        this.rentSituation = rentSituation;

    }
    @Override
    public int getCount() {
        if (roorNumber!=null){
            return roorNumber.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return roorNumber.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view ==null){
            view =View.inflate(context, R.layout.room_item,null);
            viewHolder= new ViewHolder(view);
            tvRoomNum = (TextView) view.findViewById(R.id.tvRoomNum);
            tvRentSituation = (TextView) view.findViewById(R.id.tvRentSituation);
            view.setTag(viewHolder);

        }else {
            viewHolder = ((ViewHolder) view.getTag());
        }
        tvRoomNum.setText(roorNumber.get(i).toString());
        tvRentSituation.setText(rentSituation.get(i).toString());

        return view;
    }

    class ViewHolder {
        public TextView roomNumber;
        public TextView rentSituation;
        public ViewHolder(View view){}
    }

   class roomRent {

        private String roomNumber;
        private String rentSituation;

        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public String getRentSituation() {
            return rentSituation;
        }

        public void setRentSituation(String rentSituation) {
            this.rentSituation = rentSituation;
        }
    }

}
