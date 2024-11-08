package com.mobile.productsale;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mobile.productsale.Util.ApiConfig;
import com.mobile.productsale.Util.IconLoader;
import com.mobile.productsale.Util.Validate;
import com.mobile.productsale.api.RequestUser;
import com.mobile.productsale.model.BodyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        EditText userName = findViewById(R.id.usernameText);
        EditText password = findViewById(R.id.passwordText);

        Intent getUser = getIntent();
        if (getUser.hasExtra("userName")) {
            String user = getUser.getStringExtra("userName");
            userName.setText(user);
        }

        ImageView imageView1 = findViewById(R.id.facebookLogo);
        ImageView imageView2 = findViewById(R.id.googleLogo);

        IconLoader.loadImage(this, IconLoader.fb, imageView1);
        IconLoader.loadImage(this, IconLoader.gg, imageView2);

        TextView register = findViewById(R.id.registerRedirect);
        Button loginBtn = findViewById(R.id.loginBtn);

        RequestUser requestUser = ApiConfig.getRetrofit().create(RequestUser.class);

        register.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        loginBtn.setOnClickListener(v -> {

            if (Validate.Field(userName, "Please enter a username") ||
                    Validate.Field(password, "Please enter a password")) {

                requestUser.login(userName.getText().toString().trim(), password.getText().toString().trim())
                        .enqueue(new Callback<BodyResponse>() {
                            @Override
                            public void onResponse(@NonNull Call<BodyResponse> call, @NonNull Response<BodyResponse> response) {

                                if (response.body()!=null){
                                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

                                    if (response.body().getStatusCode() == 200){
                                        //Login xong về trang home
                                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//                                        intent.putExtra("userName", userName.getText().toString());
                                        startActivity(intent);
                                    }
                                }

                            }

                            @Override
                            public void onFailure(Call<BodyResponse> call, Throwable t) {
                                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }


        });

    }

    private void Login(){

    }

}