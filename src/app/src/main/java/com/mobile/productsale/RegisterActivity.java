package com.mobile.productsale;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mobile.productsale.Util.ApiConfig;
import com.mobile.productsale.Util.IconLoader;
import com.mobile.productsale.Util.Validate;
import com.mobile.productsale.api.RequestUser;
import com.mobile.productsale.model.BodyResponse;
import com.mobile.productsale.model.RegisterDTO;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

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
        TextView inform = findViewById(R.id.informText);
        Button register = findViewById(R.id.registerBtn);

        EditText username = findViewById(R.id.usernameText);
        EditText password = findViewById(R.id.passwordText);
        EditText email = findViewById(R.id.emailText);
        EditText phone = findViewById(R.id.phoneText);
        EditText address = findViewById(R.id.addressText);

        RequestUser requestUser = ApiConfig.getRetrofit().create(RequestUser.class);

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
            if (registerSwitcher.getDisplayedChild() == 1) {
                if (Validate.isValidEmail(email) && Validate.isValidPhoneNumber(phone)) {

                    RegisterDTO registerDTO = new RegisterDTO(
                            username.getText().toString(),
                            password.getText().toString(),
                            email.getText().toString(),
                            phone.getText().toString(),
                            address.getText().toString()
                    );

                    //Kiểm tra xem email đã tồn tại hay chưa
                    requestUser.getUser(1,email.getText().toString().trim())
                            .enqueue(new Callback<BodyResponse>() {

                                @Override
                                public void onResponse( Call<BodyResponse> call,
                                                        Response<BodyResponse> response) {
                                    if (response.body() != null) {
                                        if (response.body().getContent() != null){
                                            username.setError("Email is already existed");
                                            username.requestFocus();

                                        } else if (response.body().getContent() == null){

                                            requestUser.registration(registerDTO).enqueue(new Callback<BodyResponse>() {
                                                @Override
                                                public void onResponse(@NonNull Call<BodyResponse> call, @NonNull Response<BodyResponse> response) {
                                                    if (response.body() != null) {
                                                        Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

                                                        //Register xong về trang login
                                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                                        intent.putExtra("userName", username.getText().toString());
                                                        startActivity(intent);
                                                    }
                                                }

                                                @Override
                                                public void onFailure(@NonNull Call<BodyResponse> call, @NonNull Throwable t) {
                                                    Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                                }
                                            });
                                        }
                                    } else {
                                        Toast.makeText(RegisterActivity.this,"Server error. Please contact admin", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure( Call<BodyResponse> call,  Throwable t) {
                                    Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });

                }
            } else if (registerSwitcher.getDisplayedChild() == 0 &&
                    Validate.Field(username, "Please enter a username") ||
                    Validate.Field(password, "Please enter a password")) {

                //Kiểm tra xem username đã tồn tại chưa
                requestUser.getUser(0,username.getText().toString().trim())
                        .enqueue(new Callback<BodyResponse>() {

                    @Override
                    public void onResponse( Call<BodyResponse> call,
                                            Response<BodyResponse> response) {
                        if (response.body() != null) {
                            if (response.body().getContent() != null){
                                username.setError("Username is already existed");
                                username.requestFocus();

                            } else if (response.body().getContent() == null){
                                registerSwitcher.showNext();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this,"Server error. Please contact admin", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure( Call<BodyResponse> call,  Throwable t) {
                        Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

}



