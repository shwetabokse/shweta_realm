package com.realmretrofitdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.MyViewHolder> {

    protected int layout_id;
    protected List<?> dataList = new ArrayList<>();
    protected Context BASE_CONTEXT;
    protected View itemview;

    public BaseAdapter(Context context) {
        this.BASE_CONTEXT = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout_id, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
        if (dataList.size()>0)
        onBindViewHold(position, dataList.get(position),itemview);
    }



    @Override
    public int getItemCount() {
        return dataList.size() == 0 ? 10 : dataList.size();
    }


    public abstract void onBindViewHold(int position, Object data,View itemview);

    class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemview = itemView;

        }

    }

    public <T extends View> T bind(int id) {
        return itemview.findViewById(id);
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }
}