package com.mobile.productsale;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.productsale.model.ProductDTO;
import com.mobile.productsale.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private ProductService productService;
    private ProductAdapter adapter;
    private List<ProductDTO> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button sortButton = findViewById(R.id.sortButton);
        Button filterButton = findViewById(R.id.filterButton);

        sortButton.setOnClickListener(view -> showPopup(R.layout.sort, sortButton));
        filterButton.setOnClickListener(view -> showPopup(R.layout.filter, filterButton));

        // Khởi tạo danh sách và adapter
        productList = new ArrayList<>();
        adapter = new ProductAdapter(productList, this::openProductDetail);

        // Cấu hình RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Khởi tạo ProductService và gọi API
        productService = new ProductService();
        fetchProducts();

        // Click vào icon cart
        ImageView cartIcon = findViewById(R.id.cartIcon);
        cartIcon.setOnClickListener(v -> navigateToCart());

        // Click vào icon chat
        ImageView chatIcon = findViewById(R.id.chatIcon);
        chatIcon.setOnClickListener(v -> navigateToChat());

    }

    private void navigateToCart() {
        Intent intent = new Intent(HomeActivity.this, CartActivity.class);
        startActivity(intent);
    }

    private void navigateToChat() {
        Intent intent = new Intent(HomeActivity.this, ChatRoomActivity.class);
        startActivity(intent);
    }

    private void fetchProducts() {
        productService.getProduct(new Callback<List<ProductDTO>>() {
            @Override
            public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ProductDTO> products = response.body();
                    Log.d("HomeActivity", "Products: " + products.toString());
                    // Kiểm tra giá trị của imageUrl
                    for (ProductDTO product : products) {
                        Log.d("HomeActivity", "Product Image URL: " + product.getImageUrl());
                    }
                    // Cập nhật danh sách sản phẩm và thông báo adapter
                    productList.clear();
                    productList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("HomeActivity", "Failed to fetch products. Response code: " + response.code());
                    Toast.makeText(HomeActivity.this, "Failed to fetch products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showPopup(int layoutResId, View anchorView) {
        View popupView = LayoutInflater.from(this).inflate(layoutResId, null);
        PopupWindow popupWindow = new PopupWindow(
                popupView,
                androidx.appcompat.widget.LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                androidx.appcompat.widget.LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                true
        );

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(anchorView, 0, 10);
    }

    private void openProductDetail(ProductDTO product) {
        Intent intent = new Intent(HomeActivity.this, ProductDetailActivity.class);
        intent.putExtra("product_id", product.getProductId());
        intent.putExtra("product_name", product.getProductName());
        intent.putExtra("product_brief_description", product.getBriefDescription());
        intent.putExtra("product_full_description", product.getFullDescription());
        intent.putExtra("product_specs", product.getTechnicalSpecifications());
        intent.putExtra("product_category", product.getCategory());
        intent.putExtra("product_price", String.valueOf(product.getPrice()));
        intent.putExtra("product_image_url", product.getImageUrl());
        startActivity(intent);
    }
}
