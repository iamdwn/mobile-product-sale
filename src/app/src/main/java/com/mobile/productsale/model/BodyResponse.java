package com.mobile.productsale.model;

import java.util.List;

public class BodyResponse {
    private String content;
    private String message;
    private List<String> details;
    private int statusCode;
    private Object meatadataDTO;

    public BodyResponse() {
    }

    public BodyResponse(String content, String message, List<String> details, int statusCode, Object meatadataDTO) {
        this.content = content;
        this.message = message;
        this.details = details;
        this.statusCode = statusCode;
        this.meatadataDTO = meatadataDTO;
    }

    public String getContent() {
        return content;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getDetails() {
        return details;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Object getMeatadataDTO() {
        return meatadataDTO;
    }
}
