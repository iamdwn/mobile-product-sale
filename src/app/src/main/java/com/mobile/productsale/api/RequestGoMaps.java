package com.mobile.productsale.api;

import com.mobile.productsale.model.PaymentStatusResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RequestGoMaps {
    @GET("api/GoMapsPro/get-location/{query}")
    Call<String> getLocation(@Path("query") String query);
}
