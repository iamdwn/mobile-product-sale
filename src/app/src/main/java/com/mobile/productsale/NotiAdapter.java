package com.mobile.productsale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobile.productsale.Util.ApiConfig;
import com.mobile.productsale.api.RequestNoti;
import com.mobile.productsale.api.RequestUser;
import com.mobile.productsale.model.BodyResponse;
import com.mobile.productsale.model.Notification;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.ViewHolder> {


    private List<Notification> notifications;
    private Context context;

    public NotiAdapter(List<Notification> notifications, Context context) {
        this.notifications = notifications;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.noti_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (notifications != null) {
            Notification noti = notifications.get(position);

            RequestNoti requestNoti = ApiConfig.getRetrofit().create(RequestNoti.class);

            holder.textView.setText(noti.getMessage());

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            holder.date.setText(noti.getCreatedAt());

            holder.itemView.setOnClickListener(v -> {

                //Read noti
                requestNoti.readNoti(noti.getNotificationId()).enqueue(new Callback<BodyResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<BodyResponse> call, @NonNull Response<BodyResponse> response) {
                        if (response.body().getStatusCode() == 200) {
                            Toast.makeText(context, "Noti updated success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<BodyResponse> call, @NonNull Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            });

            holder.button.setOnClickListener(v -> {
                requestNoti.removeNoti(List.of(noti.getNotificationId())).enqueue(new Callback<BodyResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<BodyResponse> call, @NonNull Response<BodyResponse> response) {
                        if (response.body().getStatusCode() == 200) {
                            Toast.makeText(context, "Noti deleted success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<BodyResponse> call, @NonNull Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            });
        }
//            Toast.makeText(context, "You have no notifications", Toast.LENGTH_LONG).show();
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private Button button;
        private TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image_view);
            textView = itemView.findViewById(R.id.item_text);
            button = itemView.findViewById(R.id.close_icon);
            date = itemView.findViewById(R.id.item_date_time);
        }
    }


}
