package com.mobile.productsale.api;

import com.mobile.productsale.model.BodyResponse;
import com.mobile.productsale.model.RegisterDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestUser {
    @POST("/api/Account/registration")
    Call<BodyResponse> registration(@Body RegisterDTO registerDTO);

    @POST("/api/Account/authen")
    Call<BodyResponse> login(@Query("username") String username,
                             @Query("password") String password);

    @GET("/api/Account/user")
    Call<BodyResponse> getUser(@Query("type") int type,
                               @Query("content") String content);

}
