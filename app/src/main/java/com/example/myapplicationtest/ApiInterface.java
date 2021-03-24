package com.example.myapplicationtest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    String BASE_URL ="http://jsonplaceholder.typicode.com/";
    @GET("users")
    Call<List<UsersModel>> usersdata();
}
