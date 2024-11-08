package com.mobile.productsale.model;

public class ResponseMessageDTO {
    private String message;

    // Constructor
    public ResponseMessageDTO(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
