package com.mobile.productsale.api;

import com.mobile.productsale.model.BodyResponse;
import com.mobile.productsale.model.LoginDTO;
import com.mobile.productsale.model.RegisterDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestUser {
    @POST("/api/Account/registration")
    Call<BodyResponse> registration(@Body RegisterDTO registerDTO);

    @POST("api/Account/authen")
    Call<BodyResponse> login(@Body LoginDTO loginDTO);

    @FormUrlEncoded
    @GET("/api/Account/user")
    Call<BodyResponse> getUser(@Field("type") int type,
                               @Field("content") String content);

}
