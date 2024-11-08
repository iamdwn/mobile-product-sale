package com.mobile.productsale.Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {
    public static String url = "https://product-sale.iamdwn.dev";

    public static Retrofit getRetrofit() {
        // Create a custom Gson instance with a date format
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") // Set the date format
                .create();

        // Create and return the Retrofit instance using the custom Gson
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson)) // Pass the custom Gson instance
                .build();
    }
}
