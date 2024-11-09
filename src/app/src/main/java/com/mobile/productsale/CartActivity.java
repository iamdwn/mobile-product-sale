package com.mobile.productsale;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.productsale.model.CartDTO;
import com.mobile.productsale.model.CartItem;
import com.mobile.productsale.model.CartItemDTO;
import com.mobile.productsale.model.ResponseMessageDTO;
import com.mobile.productsale.services.CartService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textTotalAmount;
    private List<CartItem> cartItems = new ArrayList<>();
    private CartAdapter cartAdapter;
    private CartService cartService;
    private  int cartId;
    private Button checkoutButton;
    private Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Hiển thị nút "Back"
            getSupportActionBar().setTitle("Your cart"); // Đặt tiêu đề header
        }

        recyclerView = findViewById(R.id.recyclerViewCartItems);
        textTotalAmount = findViewById(R.id.textTotalAmount);
        checkoutButton = findViewById(R.id.buttonCheckout);
        clearButton = findViewById(R.id.buttonClear);
        cartService = new CartService();

        // Set up RecyclerView and Adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(cartItems, this, cartService);
        recyclerView.setAdapter(cartAdapter);

        // Load cart items from the data source
        loadCartItems();

        checkoutButton.setOnClickListener(view -> {
            int orderId = 3;
            Intent intent = new Intent(CartActivity.this, VietQRPaymentActivity.class);
            intent.putExtra("orderId", orderId);
            startActivity(intent);
        });

        clearButton.setOnClickListener(view -> {
            clearCartItems();
        });
    }

    // Xử lý sự kiện khi nhấn nút "Back" trên Toolbar
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(CartActivity.this, HomeActivity.class); // Kết thúc Activity để quay lại HomeActivity
        startActivity(intent);
        return true;
    }

    private  void clearCartItems() {
        cartService.clearCart(cartId, new Callback<ResponseMessageDTO>() {
            @Override
            public void onResponse(Call<ResponseMessageDTO> call, Response<ResponseMessageDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Clear the existing items and map CartItemDTO objects to CartItem objects
                    cartItems.clear();
                    // Notify the adapter of data changes
                    cartAdapter.notifyDataSetChanged(); // This will update the RecyclerView
                    // Update the total amount with the newly loaded items
                    updateTotalAmount();
                } else {
                    Toast.makeText(CartActivity.this, "Không thể tải giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMessageDTO> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCartItems() {
        cartService.getCartByUser(1, new Callback<CartDTO>() {
            @Override
            public void onResponse(Call<CartDTO> call, Response<CartDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CartDTO cartDTO = response.body();
                    cartId = cartDTO.getCartId();
                    // Clear the existing items and map CartItemDTO objects to CartItem objects
                    cartItems.clear();
                    for (CartItemDTO cartItemDTO : cartDTO.getCartItems()) {
                        CartItem cartItem = new CartItem(
                                cartItemDTO.getProductId(),
                                cartItemDTO.getProductName(),
                                cartItemDTO.getPrice().doubleValue(),
                                cartItemDTO.getQuantity()
                        );
                        cartItems.add(cartItem);
                    }

                    // Notify the adapter of data changes
                    cartAdapter.notifyDataSetChanged(); // This will update the RecyclerView

                    // Update the total amount with the newly loaded items
                    updateTotalAmount();
                } else {
                    Toast.makeText(CartActivity.this, "Không thể tải giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartDTO> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void updateTotalAmount() {
        double totalAmount = 0;
        for (CartItem item : cartItems) {
            totalAmount += item.getPrice() * item.getQuantity();
        }
        textTotalAmount.setText(String.format("$%.2f", totalAmount));
    }

    public int getCartId() {
        return cartId;
    }
}
