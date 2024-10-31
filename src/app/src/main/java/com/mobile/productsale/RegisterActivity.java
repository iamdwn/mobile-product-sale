package com.mobile.productsale;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register);

        ImageView fb = findViewById(R.id.facebookLogo);
        ImageView gg = findViewById(R.id.googleLogo);


        IconLoader.loadImage(this, IconLoader.fb, fb);
        IconLoader.loadImage(this, IconLoader.gg, gg);

//      get animation
        Animation toRight = AnimationUtils.loadAnimation(RegisterActivity.this, android.R.anim.slide_out_right);
        Animation toLeft = AnimationUtils.loadAnimation(RegisterActivity.this, android.R.anim.slide_in_left);

        ViewSwitcher registerSwitcher = findViewById(R.id.continueRegister);
        TextView previousPage = findViewById(R.id.backToLogin);
        TextView returnToLogin = findViewById(R.id.loginRedirect);
        Button register = findViewById(R.id.registerBtn);

//        set animation
//        returnToLogin.setAnimation(toLeft);

        previousPage.setOnClickListener(v -> {
            if (registerSwitcher.getDisplayedChild() == 1) {
                registerSwitcher.setInAnimation(toLeft);
                registerSwitcher.showPrevious();
            } else {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        returnToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        });

        register.setOnClickListener(v -> {
            registerSwitcher.showNext();
        });

    }
}
