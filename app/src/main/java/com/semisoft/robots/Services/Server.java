package com.semisoft.robots.Services;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Server {
    @POST("login.php")
    Call<ServerResponse> login(@Query("email") String email, @Query("password") String password);
}
