package com.mobile.productsale.services;

import com.mobile.productsale.api.RequestPayment;
import com.mobile.productsale.model.Payment;
import com.mobile.productsale.model.PaymentDTO;
import com.mobile.productsale.model.PaymentStatusResponse;
import com.mobile.productsale.model.VietQrResponse;
import com.mobile.productsale.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;

public class PaymentService {
    private final RequestPayment paymentApi;

    public PaymentService() {
        paymentApi = ApiClient.getRetrofitInstance().create(RequestPayment.class);
    }

    public void createPayment(PaymentDTO paymentDTO, Callback<Payment> callback) {
        Call<Payment> call = paymentApi.createPayment(paymentDTO);
        call.enqueue(callback);
    }

    public Call<VietQrResponse> getVietQR(int paymentId, Callback<VietQrResponse> callback) {
        Call<VietQrResponse> call = paymentApi.getVietQR(paymentId);
        call.enqueue(callback);
        return call;
    }

    public void completePayment(int paymentId, Callback<Void> callback) {
        Call<Void> call = paymentApi.completePayment(paymentId);
        call.enqueue(callback);
    }

    public void getPaymentStatus(int paymentId, Callback<PaymentStatusResponse> callback) {
        Call<PaymentStatusResponse> call = paymentApi.getPaymentStatus(paymentId);
        call.enqueue(callback);
    }
}
