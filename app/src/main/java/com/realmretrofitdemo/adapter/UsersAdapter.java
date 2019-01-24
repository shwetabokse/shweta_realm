package com.realmretrofitdemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import com.realmretrofitdemo.R;
import com.realmretrofitdemo.model.UsersModel;

import java.util.List;

public class UsersAdapter extends BaseAdapter {


    public UsersAdapter(Context context, List<UsersModel> arraylist) {
        super(context);
        layout_id = R.layout.row_users;
        dataList = arraylist;

    }

    @Override
    public void onBindViewHold(int position, final Object object, View itemview) {
        UsersModel data = (UsersModel) object;
        TextView tvTitle = bind(R.id.tv_user_detail);
        tvTitle.setText(("User ID : "+data.getId())+"\n"+"User Name : "+data.getName().toUpperCase()+"\n"+"User Email : "+data.getEmail()+"\n"+"User Phone : "+data.getPhone()+"\n"+"User login id : "+data.getUsername()
                +"\n"+"User Website : "+data.getWebsite()+"\n"+"\nAddress: \n" +
                ""+data.getAddress().getStreet()+", "+data.getAddress().getCity()+", "+data.getAddress().getZipcode());

    }
}
