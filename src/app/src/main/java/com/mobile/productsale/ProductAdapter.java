package com.mobile.productsale;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate item_card.xml
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        // Bind data từ productList vào ViewHolder
        Product product = productList.get(position);
        holder.itemName.setText(product.getName());
        holder.itemPrice.setText(String.valueOf(product.getPrice()));
        holder.itemDescription.setText(product.getDescription());
        holder.itemImage.setImageResource(product.getImageResId());
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
