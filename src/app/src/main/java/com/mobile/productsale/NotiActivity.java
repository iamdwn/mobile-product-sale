package com.mobile.productsale;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mobile.productsale.Util.ApiConfig;
import com.mobile.productsale.Util.FormatJson;
import com.mobile.productsale.api.RequestNoti;
import com.mobile.productsale.api.RequestUser;
import com.mobile.productsale.model.Account;
import com.mobile.productsale.model.BodyResponse;
import com.mobile.productsale.model.Notification;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotiActivity extends AppCompatActivity {
    private RecyclerView notiView;
    private NotiAdapter notiAdapter;
    private List<Notification> listNoti;
    private Account accountInfo;
    private Gson gson;
    RequestNoti reqNoti = ApiConfig.getRetrofit().create(RequestNoti.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.noti);

        ImageView backToHome = findViewById(R.id.backToHome);

        notiView = findViewById(R.id.notiList);
        notiView.setLayoutManager(new LinearLayoutManager(this));

        accountInfo = getIntent().getParcelableExtra("accountInfo");

        reqNoti.getNotifications(accountInfo.getUserId()).enqueue(new Callback<BodyResponse>() {
            @Override
            public void onResponse(Call<BodyResponse> call, Response<BodyResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatusCode() == 200 && response.body().getContent() != null) {
                        gson = new GsonBuilder().setLenient().create();
                        String notificationJson = FormatJson.NotificationFormatter(response.body().getContent().toString());
                        Log.e("Data retrieved", "Json: " + notificationJson);
                        listNoti = new ArrayList<>();
                        listNoti = gson.fromJson(notificationJson, new TypeToken<List<Notification>>() {
                        }.getType());

                        notiAdapter = new NotiAdapter(listNoti, NotiActivity.this);
                        notiView.setAdapter(notiAdapter);

                    }


                } else {
                    Toast.makeText(NotiActivity.this, "Server error, please contact admin", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<BodyResponse> call, Throwable t) {
                Toast.makeText(NotiActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        backToHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("accountInfo", accountInfo);
            startActivity(intent);
        });

    }

}
