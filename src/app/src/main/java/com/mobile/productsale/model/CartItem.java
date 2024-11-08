package com.mobile.productsale.model;

public class CartItem {
    private int productId;
    private String productName;
    private double price;
    private int quantity;

    public CartItem(int productId, String productName, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getProductId() { return productId; }

    public void increaseQuantity() {
        quantity++;
    }

    // Decrease quantity (with a check to not go below 1)
    public void decreaseQuantity() {
        if (quantity > 1) {
            quantity--;
        }
    }
}
