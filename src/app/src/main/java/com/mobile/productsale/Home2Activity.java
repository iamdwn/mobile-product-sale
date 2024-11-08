package com.mobile.productsale;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mobile.productsale.model.Account;


public class Home2Activity extends AppCompatActivity {

    private Account accountInfo;

    private int userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);

        TextView logout = findViewById(R.id.logout);
        TextView noti = findViewById(R.id.title);

        accountInfo = getIntent().getParcelableExtra("accountInfo");

        logout.setOnClickListener(v -> {
            Intent backToLogin = new Intent(Home2Activity.this, MainActivity.class);
            startActivity(backToLogin);
        });

        noti.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Home2Activity.this, NotiActivity.class);
                intent.putExtra("accountInfo", accountInfo);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(Home2Activity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        });


    }
}
