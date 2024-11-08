package com.mobile.productsale;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.productsale.model.CartItem;
import com.mobile.productsale.model.ResponseMessageDTO;
import com.mobile.productsale.services.CartService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private CartActivity cartActivity;
    private CartService cartService;

    public CartAdapter(List<CartItem> cartItems, CartActivity cartActivity, CartService cartService) {
        this.cartItems = cartItems;
        this.cartActivity = cartActivity;
        this.cartService = cartService;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.textProductName.setText(item.getProductName());
        holder.textProductPrice.setText(String.format("$%.2f", item.getPrice()));
        holder.textQuantity.setText(String.valueOf(item.getQuantity()));

        holder.buttonIncrease.setOnClickListener(v -> {
            item.increaseQuantity();
            notifyItemChanged(position); // Update the item in the RecyclerView
            cartActivity.updateTotalAmount();  // Update the total amount in the activity

            // Call API to update item quantity in cart
            cartService.addToCart(item.getProductId(), cartActivity.getCartId(), new Callback<ResponseMessageDTO>() {
                @Override
                public void onResponse(Call<ResponseMessageDTO> call, Response<ResponseMessageDTO> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(cartActivity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(cartActivity, "Failed to update cart", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseMessageDTO> call, Throwable t) {
                    Toast.makeText(cartActivity, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        holder.buttonDecrease.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.decreaseQuantity();
                notifyItemChanged(position);  // Update the item in the RecyclerView
                cartActivity.updateTotalAmount();  // Update the total amount in the activity

                // Call API to update item quantity in cart
                cartService.removeFromCart(item.getProductId(), cartActivity.getCartId(), new Callback<ResponseMessageDTO>() {
                    @Override
                    public void onResponse(Call<ResponseMessageDTO> call, Response<ResponseMessageDTO> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Toast.makeText(cartActivity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(cartActivity, "Failed to remove from cart", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseMessageDTO> call, Throwable t) {
                        Toast.makeText(cartActivity, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView textProductName, textProductPrice, textQuantity;
        Button buttonIncrease, buttonDecrease;

        CartViewHolder(View itemView) {
            super(itemView);
            textProductName = itemView.findViewById(R.id.textProductName);
            textProductPrice = itemView.findViewById(R.id.textPrice);
            textQuantity = itemView.findViewById(R.id.textQuantity);
            buttonIncrease = itemView.findViewById(R.id.buttonIncrease);
            buttonDecrease = itemView.findViewById(R.id.buttonDecrease);
        }
    }
}
