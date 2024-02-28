package com.semisoft.robots.Services;

import com.semisoft.robots.Domain.User;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Server {
    @GET("login.php")
    Call<ServerResponse> login(@Query("email") String email, @Query("password") String password);
}
