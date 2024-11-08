package com.mobile.productsale;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.mobile.productsale.R;
import com.squareup.picasso.Picasso;

public class PaymentActivity extends AppCompatActivity {
    private RadioGroup paymentMethodGroup;
    private ImageView qrCodeImageView;
    private TextView amountTextView;
    private Button confirmPaymentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        paymentMethodGroup = findViewById(R.id.paymentMethodGroup);
        qrCodeImageView = findViewById(R.id.qrCodeImageView);
        amountTextView = findViewById(R.id.amountTextView);
        confirmPaymentButton = findViewById(R.id.confirmPaymentButton);

        // Display the total amount
        amountTextView.setText("Amount: 100,000 VND");

        // Payment method selection logic
        paymentMethodGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbVietQR) {
                displayQRCode();
            } else {
                qrCodeImageView.setVisibility(View.GONE);
            }
        });

        // Confirm payment button
        confirmPaymentButton.setOnClickListener(v -> {
            int selectedMethod = paymentMethodGroup.getCheckedRadioButtonId();
            if (selectedMethod == R.id.rbVietQR) {
                confirmPaymentVietQR();
            } else if (selectedMethod == R.id.rbVNPay) {
                // Implement VNPay payment here
            } else if (selectedMethod == R.id.rbZaloPay) {
                // Implement ZaloPay payment here
            } else if (selectedMethod == R.id.rbPayPal) {
                // Implement PayPal payment here
            }
        });
    }

    private void displayQRCode() {
        // Call backend API to get QR code URL
        String qrUrl = "https://your-api-url/api/Payment/GetVietQR/123"; // Replace with actual API call

        // Load QR code using Picasso or Glide
        Picasso.get().load(qrUrl).into(qrCodeImageView);
        qrCodeImageView.setVisibility(View.VISIBLE);
    }

    private void confirmPaymentVietQR() {
        // Implement confirmation and show order summary
        Toast.makeText(this, "Payment Confirmed via VietQR", Toast.LENGTH_SHORT).show();
        // Navigate to order confirmation screen or update UI to show payment status
    }
}
