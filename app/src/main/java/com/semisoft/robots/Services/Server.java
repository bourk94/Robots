package com.semisoft.robots.Services;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Server {
    @POST("api/login")
    @FormUrlEncoded
    Call<ServerResponse> login(@Field("email") String email, @Field("password") String password);
}
