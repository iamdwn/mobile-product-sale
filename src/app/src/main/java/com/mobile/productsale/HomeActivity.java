package com.mobile.productsale;

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
import android.content.Intent;
import android.widget.TextView;
import androidx.annotation.Nullable;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home_activity);

        // Set padding để hỗ trợ Edge to Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Lấy tham chiếu tới nút Sort và Filter
        Button sortButton = findViewById(R.id.sortButton);
        Button filterButton = findViewById(R.id.filterButton);

        // Sự kiện nhấn nút Sort
        sortButton.setOnClickListener(view -> showPopup(R.layout.sort, sortButton));

        // Sự kiện nhấn nút Filter
        filterButton.setOnClickListener(view -> showPopup(R.layout.filter, filterButton));

        // Khởi tạo danh sách sản phẩm
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Product 1", "Description 1", 100.0, R.drawable.ava3));
        productList.add(new Product("Product 2", "Description 2", 200.0, R.drawable.ava3));
        productList.add(new Product("Product 3", "Description 3", 300.0, R.drawable.ava3));

        // Khởi tạo RecyclerView và gắn Adapter
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Đặt LayoutManager cho RecyclerView
        ProductAdapter adapter = new ProductAdapter(productList); // Tạo adapter và truyền danh sách sản phẩm vào
        recyclerView.setAdapter(adapter); // Gắn adapter vào RecyclerView
    }

    // Phương thức hiển thị PopupWindow cho Sort và Filter
    private void showPopup(int layoutResId, View anchorView) {
        // Inflate layout cho popup từ layout XML
        View popupView = LayoutInflater.from(this).inflate(layoutResId, null);

        // Tạo PopupWindow
        PopupWindow popupWindow = new PopupWindow(
                popupView,
                androidx.appcompat.widget.LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                androidx.appcompat.widget.LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                true
        );

        // Cải tiến: Thêm padding hoặc margin nếu cần
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true); // Cho phép tắt popup khi nhấn ra ngoài

        // Hiển thị PopupWindow ngay bên dưới nút được nhấn
        popupWindow.showAsDropDown(anchorView, 0, 10); // Điều chỉnh margin (x: 0, y: 10)

//     protected void onCreate(@Nullable Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);

//         setContentView(R.layout.home);

//         TextView logout = findViewById(R.id.logout);

//         logout.setOnClickListener(v -> {
//             Intent backToLogin = new Intent(HomeActivity.this, MainActivity.class);
//             startActivity(backToLogin);
//         });
    }
}
