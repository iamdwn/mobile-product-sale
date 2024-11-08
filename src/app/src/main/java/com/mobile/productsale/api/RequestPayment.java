package com.mobile.productsale.api;

import com.mobile.productsale.model.PayOSPaymentRequestDTO;
import com.mobile.productsale.model.PaymentStatusResponse;
import com.mobile.productsale.model.VietQrResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RequestPayment {
    @GET("api/payment/get-vietqr/{paymentId}")
    Call<VietQrResponse> getVietQR(@Path("paymentId") int paymentId);

    @GET("api/payment/get-status/{paymentId}")
    Call<PaymentStatusResponse> getPaymentStatus(@Path("paymentId") int paymentId);

    @POST("api/payment/payos")
    Call<VietQrResponse> createPayOSPayment(@Body PayOSPaymentRequestDTO request);

    @POST("api/payment/cancel")
    Call<Object> cancelPayOSPayment(long orderCode, String reason);

    @PUT("api/payment")
    Call<Void> updatePayment(@Body PayOSPaymentRequestDTO paymentReq);

    @DELETE("api/payment/{paymentId}")
    Call<Void> removePayment(@Path("paymentId") int paymentId);

    @POST("api/payment/complete-payment/{paymentId}")
    Call<Void> completePayment(@Path("paymentId") int paymentId);


}
