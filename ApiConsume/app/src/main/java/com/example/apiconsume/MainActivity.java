package com.example.apiconsume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.apiconsume.data.RestClients;
import com.example.apiconsume.data.UserAdapter;
import com.example.apiconsume.pojo.User;
import com.example.apiconsume.pojo.UserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<User> listUser;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        RestClients.getService().getAllUser().enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                if (response.isSuccessful()){
                    listUser = response.body().getData();
                    adapter = new UserAdapter(listUser, MainActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager((getApplicationContext())));
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.d("Failure" ,t.getMessage());
            }
        });
    }
}