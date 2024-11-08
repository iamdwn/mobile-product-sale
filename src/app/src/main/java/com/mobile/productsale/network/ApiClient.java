package com.mobile.productsale.network;

import com.mobile.productsale.interceptor.RetryInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://product-sale.iamdwn.dev/";
//    private static final String BASE_URL = "http://localhost:5087/";
    private static Retrofit retrofit;

    static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new RetryInterceptor(3))
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
//                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

