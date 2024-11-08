package com.mobile.productsale;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView productName, productPrice, productFullDescription, productTechnicalSpecifications, productCategory;
    private ImageView productImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        // Thiết lập Toolbar làm header với nút "Back"
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Hiển thị nút "Back"
            getSupportActionBar().setTitle("Product Details"); // Đặt tiêu đề header
        }

        // Ánh xạ các view
        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);
        productFullDescription = findViewById(R.id.product_full_description);
        productTechnicalSpecifications = findViewById(R.id.product_technical_specifications);
        productCategory = findViewById(R.id.product_category);
        productImage = findViewById(R.id.product_image);

        // Nhận dữ liệu từ Intent
        String name = getIntent().getStringExtra("product_name");
        String price = getIntent().getStringExtra("product_price");
        String fullDescription = getIntent().getStringExtra("product_full_description");
        String technicalSpecs = getIntent().getStringExtra("product_specs");
        String category = getIntent().getStringExtra("product_category");
        String imageUrl = getIntent().getStringExtra("product_image_url");

        // Cập nhật các view với dữ liệu
        if (name != null) productName.setText(name);
        if (price != null) productPrice.setText("Price: $" + price);
        if (fullDescription != null) productFullDescription.setText(fullDescription);
        if (technicalSpecs != null) productTechnicalSpecifications.setText(technicalSpecs);
        if (category != null) productCategory.setText("Category: " + category);

        // Hiển thị hình ảnh (sử dụng Glide để tải từ URL)
        if (imageUrl != null) {
            Glide.with(this).load(imageUrl).into(productImage);
        } else {
            // Nếu imageUrl null, có thể đặt một hình ảnh mặc định
            productImage.setImageResource(R.drawable.ava3);
        }
    }

    // Xử lý sự kiện khi nhấn nút "Back" trên Toolbar
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Kết thúc Activity để quay lại HomeActivity
        return true;
    }
}
