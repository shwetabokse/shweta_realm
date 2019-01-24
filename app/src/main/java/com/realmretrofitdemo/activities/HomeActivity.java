package com.realmretrofitdemo.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.realmretrofitdemo.R;
import com.realmretrofitdemo.adapter.UsersAdapter;
import com.realmretrofitdemo.helper.ApiClient;
import com.realmretrofitdemo.helper.ApiInterface;
import com.realmretrofitdemo.model.UsersModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


public class HomeActivity extends AppCompatActivity {

    Realm realm;


    RecyclerView recycler_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_user = findViewById(R.id.recycler_user);


        realm = Realm.getDefaultInstance();


        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(UsersModel.class);
            }
        });


        callApi();

    }


    private void callApi() {

        final ProgressDialog dialog = new ProgressDialog(HomeActivity.this);
        dialog.setMessage("Please wait...");
        dialog.show();
        final ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<UsersModel>> call = apiService.getUsers();
        call.enqueue(new Callback<List<UsersModel>>() {
            @Override
            public void onResponse(Response<List<UsersModel>> response) {

                List<UsersModel> clientModel = response.body();

                realm.beginTransaction();

                for (UsersModel data : clientModel) {
                    realm.copyToRealm(data);
                }

                dialog.dismiss();
                // Saved data in realm , Here it will set adapter with data stored in realm.

                setAdapter();

            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("", t.toString());
                dialog.dismiss();

            }
        });
    }

    private void setAdapter() {


        RealmResults<UsersModel> usersList = realm.where(UsersModel.class).findAll();
        recycler_user.setHasFixedSize(false);
        recycler_user.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycler_user.setAdapter(new UsersAdapter(getApplicationContext(), usersList));
        realm.commitTransaction();
       // realm.close();
    }


}
