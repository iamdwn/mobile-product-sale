package com.mobile.productsale;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView imageView1 = findViewById(R.id.facebookLogo);
        ImageView imageView2 = findViewById(R.id.googleLogo);

        String fb = "https://freepnglogo.com/images/all_img/facebook-circle-logo-png.png";
        String gg = "https://cdn1.iconfinder.com/data/icons/google-s-logo/150/Google_Icons-09-512.png";

        Glide.with(this)
                .load(fb)
                .into(imageView1);

        Glide.with(this)
                .load(gg)
                .into(imageView2);

        TextView register = findViewById(R.id.registerRedirect);

        register.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

    }
}