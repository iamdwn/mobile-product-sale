package com.mobile.productsale.model;

public class CompletePaymentDTO {
    private int paymentId;

    public CompletePaymentDTO(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
}
