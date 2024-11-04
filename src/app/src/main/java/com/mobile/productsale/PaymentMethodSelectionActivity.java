package com.mobile.productsale;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaymentMethodSelectionActivity extends AppCompatActivity {
    private Button buttonVietQR, buttonCard, buttonCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_method_selection);

        buttonVietQR = findViewById(R.id.buttonVietQR);
        buttonCard = findViewById(R.id.buttonCard);
        buttonCash = findViewById(R.id.buttonCash);

        buttonVietQR.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentMethodSelectionActivity.this, VietQRPaymentActivity.class);
            startActivity(intent);
        });

        buttonCard.setOnClickListener(v -> {
        });

        buttonCash.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentMethodSelectionActivity.this, OrderConfirmationActivity.class);
            startActivity(intent);
        });
    }
}
