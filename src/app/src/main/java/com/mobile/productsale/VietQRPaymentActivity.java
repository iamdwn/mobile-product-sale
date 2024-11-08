package com.mobile.productsale;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.mobile.productsale.model.PayOSPaymentRequestDTO;
import com.mobile.productsale.model.PaymentStatusResponse;
import com.mobile.productsale.model.VietQrResponse;
import com.mobile.productsale.services.PaymentService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.os.Handler;
import android.os.Looper;

public class VietQRPaymentActivity extends AppCompatActivity {

    private ImageView qrImageView;
    private WebView qrWebView;
    private Button completePaymentButton;
    private PaymentService paymentService;
    private int paymentId;
    private PayOSPaymentRequestDTO payOSPaymentRequestDTO;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private static final int POLLING_INTERVAL = 3000;

    public void setupPayment() {
        payOSPaymentRequestDTO = new PayOSPaymentRequestDTO(4,"");
//        paymentDTO.setOrderId(4);
//        paymentDTO.setNote("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vietqr_payment);
        payOSPaymentRequestDTO = new PayOSPaymentRequestDTO(12,"test oce chua?");

        qrImageView = findViewById(R.id.qrImageView);
//        qrWebView = findViewById(R.id.qrWebView);
        completePaymentButton = findViewById(R.id.completePaymentButton);

        paymentService = new PaymentService();
        paymentId = getIntent().getIntExtra("PAYMENT_ID", 33);

        if (paymentId != -1) {
//            fetchVietQR(paymentId);
            fetchPayOS(payOSPaymentRequestDTO);
//            startPaymentStatusPolling();
        } else {
            Toast.makeText(this, "Invalid Payment ID", Toast.LENGTH_SHORT).show();
            finish();
        }

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> finish());
        completePaymentButton.setOnClickListener(view -> {
            completePayment(paymentId);
            Intent intent = new Intent(VietQRPaymentActivity.this, PaymentSuccessActivity.class);
            startActivity(intent);
        });
    }

    private void startPaymentStatusPolling() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkPaymentStatus();
                handler.postDelayed(this, POLLING_INTERVAL);
            }
        }, POLLING_INTERVAL);
    }

    private void checkPaymentStatus() {
        paymentService.getPaymentStatus(paymentId, new Callback<PaymentStatusResponse>() {
            @Override
            public void onResponse(Call<PaymentStatusResponse> call, Response<PaymentStatusResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isCompleted()) {
                        completePayment(paymentId);
                        handler.removeCallbacksAndMessages(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<PaymentStatusResponse> call, Throwable t) {
                Toast.makeText(VietQRPaymentActivity.this, "Error checking payment status: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    private void fetchVietQR(int paymentId) {
        paymentService.getVietQR(paymentId, new Callback<VietQrResponse>() {
            @Override
            public void onResponse(Call<VietQrResponse> call, Response<VietQrResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String qrUrl = response.body().getQrUrl();
                    Picasso.get().load(qrUrl).into(qrImageView);
//                    WebView qrWebView = findViewById(R.id.qrWebView);
//                    qrWebView.getSettings().setJavaScriptEnabled(true);
//                    qrWebView.loadUrl(qrUrl);
                } else {
                    Toast.makeText(VietQRPaymentActivity.this, "Failed to load QR code", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VietQrResponse> call, Throwable t) {
                Toast.makeText(VietQRPaymentActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchPayOS(PayOSPaymentRequestDTO payOSPaymentRequestDTO) {
        paymentService.createPayOSPayment(payOSPaymentRequestDTO, new Callback<VietQrResponse>() {
            @Override
            public void onResponse(Call<VietQrResponse> call, Response<VietQrResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String qrUrl = response.body().getQrUrl();
                    Picasso.get().load(qrUrl).into(qrImageView);
//                    WebView qrWebView = findViewById(R.id.qrWebView);
//                    qrWebView.getSettings().setJavaScriptEnabled(true);
//                    qrWebView.loadUrl(qrUrl);
                } else {
                    Toast.makeText(VietQRPaymentActivity.this, "Failed to load QR code", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<VietQrResponse> call, Throwable t) {
                Toast.makeText(VietQRPaymentActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void completePayment(int paymentId) {
        paymentService.completePayment(paymentId, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(VietQRPaymentActivity.this, "Payment completed successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(VietQRPaymentActivity.this, "Failed to complete payment", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(VietQRPaymentActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
