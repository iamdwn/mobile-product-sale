package com.mobile.productsale;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.google.gson.Gson;
import com.mobile.productsale.Util.ApiConfig;
import com.mobile.productsale.Util.IconLoader;
import com.mobile.productsale.Util.Validate;
import com.mobile.productsale.api.RequestNoti;
import com.mobile.productsale.api.RequestUser;
import com.mobile.productsale.model.Account;
import com.mobile.productsale.model.BodyResponse;
import com.mobile.productsale.model.NewNotification;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RequestUser requestUser = ApiConfig.getRetrofit().create(RequestUser.class);
    RequestNoti requestNoti = ApiConfig.getRetrofit().create(RequestNoti.class);
    Account accountInfo;
    Gson gson = new Gson();


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
            getUser.removeExtra("userName");
        }

        ImageView imageView1 = findViewById(R.id.facebookLogo);
        ImageView imageView2 = findViewById(R.id.googleLogo);

        IconLoader.loadImage(this, IconLoader.fb, imageView1);
        IconLoader.loadImage(this, IconLoader.gg, imageView2);

        TextView register = findViewById(R.id.registerRedirect);
        Button loginBtn = findViewById(R.id.loginBtn);


        register.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        loginBtn.setOnClickListener(v -> {

            NewNotification no = new NewNotification(1, "");

            if (Validate.Field(userName, "Please enter a username") ||
                    Validate.Field(password, "Please enter a password")) {

                requestUser.login(userName.getText().toString().trim(), password.getText().toString().trim())
                        .enqueue(new Callback<BodyResponse>() {
                            @Override
                            public void onResponse(@NonNull Call<BodyResponse> call, @NonNull Response<BodyResponse> response) {

                                if (response.body() != null) {
                                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

                                    if (response.body().getStatusCode() == 200) {

                                        requestUser.getUser(0, userName.getText().toString().trim())
                                                .enqueue(new Callback<BodyResponse>() {
                                                    @Override
                                                    public void onResponse(Call<BodyResponse> call,
                                                                           Response<BodyResponse> response) {
                                                        if (response.body() != null) {
                                                            if (response.body().getContent() != null) {
                                                                //Login xong về trang home
                                                                accountInfo = gson.fromJson(response.body().getContent().toString(), Account.class);

                                                                //Tạo thông báo nhập cập nhật thông tin
                                                                if (accountInfo.getAddress().equals("default") || accountInfo.getPhoneNumber().equals("0352222222")) {
                                                                    requestNoti.newNoti(new NewNotification(2, "[Information] Please update your personal information)"));
                                                                    Log.i("Da goi api", "da goi api");
                                                                }

                                                                Intent intent = new Intent(MainActivity.this, Home2Activity.class);
                                                                intent.putExtra("accountInfo", accountInfo);
                                                                startActivity(intent);

                                                            } else if (response.body().getContent() == null) {
                                                                Toast.makeText(MainActivity.this, "Server error. Get user information fails, please contact admin", Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure
                                                            (Call<BodyResponse> call, Throwable t) {
                                                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                                    }
                                                });

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

}