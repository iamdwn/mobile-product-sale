package com.mobile.productsale.model;

import java.time.LocalDateTime;
import java.util.Date;

public class ChatMessageDTO {
    private int roomId;
    private Integer userId;
    private String message;
    private String sentAt;

    public ChatMessageDTO(int roomId, int userId, String messageText, String date) {
        this.roomId = roomId;
        this.userId = userId;
        this.message = messageText;
        this.sentAt = date;
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
}
