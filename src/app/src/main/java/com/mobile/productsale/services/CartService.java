package com.mobile.productsale.services;

import com.mobile.productsale.api.RequestCart;
import com.mobile.productsale.api.RequestPayment;
import com.mobile.productsale.model.CartDTO;
import com.mobile.productsale.model.Payment;
//import com.mobile.productsale.model.PaymentDTO;
import com.mobile.productsale.model.ResponseMessageDTO;
import com.mobile.productsale.model.VietQrResponse;
import com.mobile.productsale.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;

public class CartService {
    private final RequestCart cartApi;

    public CartService() {
        cartApi = ApiClient.getRetrofitInstance().create(RequestCart.class);
    }

    public void getCart(int id, Callback<CartDTO> callback) {
        Call<CartDTO> call = cartApi.getCart(id);
        call.enqueue(callback);
    }

    public void getCartByUser(int userId, Callback<CartDTO> callback) {
        Call<CartDTO> call = cartApi.getCartByUser(userId);
        call.enqueue(callback);
    }

    public void addToCart(int productId, int cartId, Callback<ResponseMessageDTO> callback) {
        Call<ResponseMessageDTO> call = cartApi.addToCart(productId, cartId);
        call.enqueue(callback);
    }

    public void removeFromCart(int productId, int cartId, Callback<ResponseMessageDTO> callback) {
        Call<ResponseMessageDTO> call = cartApi.removeFromCart(productId, cartId);
        call.enqueue(callback);
    }

}
