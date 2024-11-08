package com.mobile.productsale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobile.productsale.Util.ApiConfig;
import com.mobile.productsale.api.RequestNoti;
import com.mobile.productsale.api.RequestUser;
import com.mobile.productsale.model.BodyResponse;
import com.mobile.productsale.model.Notification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Notification noti = notifications.get(position);

        LinearLayout notiItem = holder.itemView.findViewById(R.id.noti_item);

        if (noti.isRead()) {
            notiItem.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.gray_light)); // hoặc màu xậm của bạn
        } else {
            notiItem.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
        }

        RequestNoti requestNoti = ApiConfig.getRetrofit().create(RequestNoti.class);

        holder.textView.setText(noti.getMessage());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Date createdDate = null;
        try {
            createdDate = formatter.parse(noti.getCreatedAt());
        } catch (ParseException e) {
            Log.e("Date Parsing Error", e.getMessage());
        }

        Date currentDate = new Date();

        if (createdDate != null) {
            long diffInMillis = currentDate.getTime() - createdDate.getTime();
            long minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
            long hours = TimeUnit.MILLISECONDS.toHours(diffInMillis);
            long days = TimeUnit.MILLISECONDS.toDays(diffInMillis);

            String elapsedTime;
            if (days > 0) {
                elapsedTime = days + " days";
            } else if (hours > 0) {
                elapsedTime = hours + " hours";
            } else if (minutes > 0) {
                elapsedTime = minutes + " mins";
            } else {
                elapsedTime = "recently";
            }

            holder.date.setText(elapsedTime);
        } else {
            holder.date.setText("Unknown date");
        }

        holder.itemView.setOnClickListener(v -> {
            //Read noti
            if (!noti.isRead()){
                requestNoti.readNoti(noti.getNotificationId()).enqueue(new Callback<BodyResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<BodyResponse> call, @NonNull Response<BodyResponse> response) {
                        if (response.body().getStatusCode() == 200) {
                            Toast.makeText(context, "Noti updated success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                        noti.setRead(true);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(@NonNull Call<BodyResponse> call, @NonNull Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        holder.button.setOnClickListener(v -> {
            requestNoti.removeNoti(noti.getNotificationId()).enqueue(new Callback<BodyResponse>() {
                @Override
                public void onResponse(@NonNull Call<BodyResponse> call, @NonNull Response<BodyResponse> response) {
                    if (response.body().getStatusCode() == 200) {
                        Toast.makeText(context, "Noti deleted success", Toast.LENGTH_SHORT).show();
                        notifications.remove(position); // Xóa item khỏi danh sách
                        notifyItemRemoved(position);
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
        private ImageView backToHome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image_view);
            textView = itemView.findViewById(R.id.item_text);
            button = itemView.findViewById(R.id.close_icon);
            date = itemView.findViewById(R.id.item_date_time);
            backToHome = itemView.findViewById(R.id.backToHome);
        }
    }


}
