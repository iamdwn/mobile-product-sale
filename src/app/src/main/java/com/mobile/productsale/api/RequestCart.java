package com.mobile.productsale.api;

import com.mobile.productsale.model.CartDTO;
import com.mobile.productsale.model.ResponseMessageDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestCart {
    @GET("api/cart/{id}")
    Call<CartDTO> getCart(@Path("id") int id);

    @GET("api/cart/user/{userId}")
    Call<CartDTO> getCartByUser(@Path("userId") int userId);

    @POST("api/cart/addToCart")
    Call<ResponseMessageDTO> addToCart(@Query("productId") int productId, @Query("cartId") int cartId);

    @DELETE("api/cart/removeFromCart")
    Call<ResponseMessageDTO> removeFromCart(@Query("productId") int productId, @Query("cartId") int cartId);
}
