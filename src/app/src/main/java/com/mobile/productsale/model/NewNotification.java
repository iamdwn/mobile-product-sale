package com.mobile.productsale.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewNotification {
    private int userId;
    private String message;
    private boolean isRead;
    private String createdAt;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public NewNotification(int userId, String message) {
        this.userId = userId;
        this.message = message;
        this.createdAt = formatter.format(new Date());
        this.isRead = false;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
