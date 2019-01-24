package com.realmretrofitdemo.helper;




import com.realmretrofitdemo.model.UsersModel;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;


public interface ApiInterface {

      @GET("users")
      Call<List<UsersModel>> getUsers();



}

