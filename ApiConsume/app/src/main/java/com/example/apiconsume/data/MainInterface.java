package com.example.apiconsume.data;

import com.example.apiconsume.pojo.BodyUser;
import com.example.apiconsume.pojo.UserList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MainInterface {
    // TODO Memasukkan Endpoint
    @GET("api/users")
    Call<UserList> getAllUser();
}
