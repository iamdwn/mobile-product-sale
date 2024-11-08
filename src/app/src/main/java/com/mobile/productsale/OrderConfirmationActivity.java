package com.mobile.productsale;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrderConfirmationActivity extends AppCompatActivity {
    private TextView textOrderSummary, textPaymentStatus;
    private Button buttonFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_confirmation);

        textOrderSummary = findViewById(R.id.textOrderSummary);
        textPaymentStatus = findViewById(R.id.textPaymentStatus);
        buttonFinish = findViewById(R.id.buttonFinish);

        buttonFinish.setOnClickListener(v -> finish());
    }
}
