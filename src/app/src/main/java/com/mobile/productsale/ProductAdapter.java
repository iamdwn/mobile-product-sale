package com.mobile.productsale;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.mobile.productsale.model.ProductDTO;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductDTO> productList;
    private OnItemClickListener onItemClickListener;

    // Interface để xử lý sự kiện click vào item
    public interface OnItemClickListener {
        void onItemClick(ProductDTO product);
    }

    // Constructor nhận vào danh sách sản phẩm và listener cho sự kiện click
    public ProductAdapter(List<ProductDTO> productList, OnItemClickListener onItemClickListener) {
        this.productList = productList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout của item (item_card.xml)
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        // Lấy sản phẩm hiện tại
        ProductDTO product = productList.get(position);

        // Đặt dữ liệu lên các view trong ViewHolder
        holder.itemName.setText(product.getProductName());
        holder.itemPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.itemDescription.setText(product.getBriefDescription());

        // Sử dụng Glide để tải ảnh từ URL (imageUrl)
        Glide.with(holder.itemImage.getContext())
                .load(product.getImageUrl())
                .into(holder.itemImage);

        // Thiết lập sự kiện click vào item
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(product));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // ViewHolder để giữ các view trong item_card
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice, itemDescription;
        ImageView itemImage;

        public ProductViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemDescription = itemView.findViewById(R.id.item_description);
            itemImage = itemView.findViewById(R.id.item_image);
        }
    }
}
