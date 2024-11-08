package com.mobile.productsale.model;

import java.util.Date;

public class ChatMessage {
    private int chatMessageId;
    private int roomId;
    private Integer userId;
    private String message;
    private String sentAt;

    // Constructors, getters, and setters
    public ChatMessage(int chatMessageId, int roomId, Integer userId, String message, String sentAt) {
        this.chatMessageId = chatMessageId;
        this.roomId = roomId;
        this.userId = userId;
        this.message = message;
        this.sentAt = sentAt;
    }

    public int getChatMessageId() {
        return chatMessageId;
    }

    public void setChatMessageId(int chatMessageId) {
        this.chatMessageId = chatMessageId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }


    // Getters and setters
    // (add here if needed)
}
