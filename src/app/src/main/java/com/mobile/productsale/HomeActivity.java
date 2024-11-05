package com.mobile.productsale;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);

        TextView logout = findViewById(R.id.logout);

        logout.setOnClickListener(v -> {
            Intent backToLogin = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(backToLogin);
        });
    }
}
