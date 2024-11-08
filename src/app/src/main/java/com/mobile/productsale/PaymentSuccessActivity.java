package com.mobile.productsale;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.productsale.services.PaymentService;

public class PaymentSuccessActivity extends AppCompatActivity {
    private Button buttonBackStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_success_screen);

        buttonBackStore = findViewById(R.id.buttonBackStore);

        buttonBackStore.setOnClickListener(view -> {
            Intent intent = new Intent(PaymentSuccessActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}
