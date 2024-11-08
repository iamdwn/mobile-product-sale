package com.mobile.productsale;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.mobile.productsale.model.ProductDTO;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
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

        // Khởi tạo danh sách sản phẩm với ProductDTO
        List<ProductDTO> productList = new ArrayList<>();
        productList.add(new ProductDTO("Product 1", "Brief description of Product 1", "Full description of Product 1", "Technical Specs of Product 1", "Category 1", 100.0, "https://example.com/image1.jpg"));
        productList.add(new ProductDTO("Product 2", "Brief description of Product 2", "Full description of Product 2", "Technical Specs of Product 2", "Category 2", 200.0, "https://example.com/image2.jpg"));
        productList.add(new ProductDTO("Product 3", "Brief description of Product 3", "Full description of Product 3", "Technical Specs of Product 3", "Category 3", 300.0, "https://example.com/image3.jpg"));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProductAdapter adapter = new ProductAdapter(productList, this::openProductDetail);
        recyclerView.setAdapter(adapter);
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

        intent.putExtra("product_name", product.getProductName());
        intent.putExtra("product_brief_description", product.getBriefDescription());
        intent.putExtra("product_full_description", product.getFullDescription());
        intent.putExtra("product_specs", product.getTechnicalSpecifications());
        intent.putExtra("product_category", product.getCategory());
        intent.putExtra("product_price", product.getPrice());
        intent.putExtra("product_image_url", product.getImageUrl());

        startActivity(intent);
    }
}
