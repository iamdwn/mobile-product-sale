package com.mobile.productsale;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BillingDetailsActivity extends AppCompatActivity {
    private EditText editTextName, editTextAddress, editTextPhone;
    private RadioGroup paymentMethodGroup;
    private Button buttonProceedToPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing_details);

        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhone = findViewById(R.id.editTextPhone);
        paymentMethodGroup = findViewById(R.id.paymentMethodGroup);
        buttonProceedToPayment = findViewById(R.id.buttonProceedToPayment);

        buttonProceedToPayment.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String address = editTextAddress.getText().toString();
            String phone = editTextPhone.getText().toString();

            int selectedId = paymentMethodGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedId);
            String selectedPaymentMethod = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "";

            if (name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedPaymentMethod.isEmpty()) {
                Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(BillingDetailsActivity.this, VietQRPaymentActivity.class);
            intent.putExtra("SELECTED_PAYMENT_METHOD", selectedPaymentMethod);
            intent.putExtra("USER_NAME", name);
            intent.putExtra("USER_ADDRESS", address);
            intent.putExtra("USER_PHONE", phone);
            startActivity(intent);
        });
    }
}
