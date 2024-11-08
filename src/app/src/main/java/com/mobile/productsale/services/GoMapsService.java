package com.mobile.productsale.services;

import com.mobile.productsale.api.RequestGoMaps;
import com.mobile.productsale.api.RequestPayment;
import com.mobile.productsale.model.PayOSPaymentRequestDTO;
import com.mobile.productsale.model.VietQrResponse;
import com.mobile.productsale.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;

public class GoMapsService {
    private final RequestGoMaps goMapsApi;

    public GoMapsService() {
        goMapsApi = ApiClient.getRetrofitInstance().create(RequestGoMaps.class);
    }

    public Call<String> getLocation(String query, Callback<String> callback) {
        Call<String> call = goMapsApi.getLocation(query);
        call.enqueue(callback);
        return call;
    }
}
