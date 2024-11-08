package com.mobile.productsale.api;

import com.mobile.productsale.model.BodyResponse;
import com.mobile.productsale.model.NewNotification;
import com.mobile.productsale.model.RegisterDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestNoti {
    @GET("api/Notification/get")
    Call<BodyResponse> getNotifications(@Query("userId") int userId);
    //new
    @POST("api/Notification/new")
    Call<BodyResponse> newNoti(@Body NewNotification noti);

    //read
    @POST("api/Notification/read")
    Call<BodyResponse> readNoti(@Query("notiId") int notiId);

    //remove
    @DELETE("api/Notification/remove")
    Call<BodyResponse> removeNoti(@Query("notiId") int notiId);
}
