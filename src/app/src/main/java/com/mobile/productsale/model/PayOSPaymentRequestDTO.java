package com.mobile.productsale.model;

import java.math.BigDecimal;

public class PayOSPaymentRequestDTO {
    private int PaymentId = 0;

    private int OrderId;

    private BigDecimal Amount;

    private String Note;

    public PayOSPaymentRequestDTO(int orderId, String note) {
        OrderId = orderId;
        Note = note;
    }

    public PayOSPaymentRequestDTO(int orderId) {
        OrderId = orderId;
    }

    public int getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(int paymentId) {
        PaymentId = paymentId;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }
}
