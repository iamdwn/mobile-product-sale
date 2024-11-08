package com.mobile.productsale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    private Spinner paymentMethodSpinner;
    private EditText addressEditText,phoneEditText;
    private Button confirmButton, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);

        // Initialize UI components
        paymentMethodSpinner = findViewById(R.id.paymentMethodSpinner);
        addressEditText = findViewById(R.id.addressEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        confirmButton = findViewById(R.id.confirmButton);

        // Set up Payment Methods Spinner
        ArrayAdapter<String> paymentMethodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"VietQR", "PayPal", "VNPay","ZaloPay"});
        paymentMethodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentMethodSpinner.setAdapter(paymentMethodAdapter);

        // Set up Confirm button click listener
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmOrder();
            }
        });

        // Xử lý sự kiện khi nhấn nút quay lại
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại màn hình trước
                onBackPressed();
            }
        });




    }
    Intent intent = new Intent(OrderActivity.this, PaymentActivity.class);


    private void confirmOrder() {
        // Get user input values
        String paymentMethod = paymentMethodSpinner.getSelectedItem().toString();
        String address = addressEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();

        // Validate inputs
        if (address.isEmpty() || phone.isEmpty()) {
            Toast.makeText(OrderActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            // Proceed with order confirmation
            // You can now process the order (e.g., save to database, send to API, etc.)
            Toast.makeText(OrderActivity.this, "Order confirmed with " + paymentMethod, Toast.LENGTH_SHORT).show();
        }
    }
}