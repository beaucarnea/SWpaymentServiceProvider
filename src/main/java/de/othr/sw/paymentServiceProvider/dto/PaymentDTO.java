package de.othr.sw.paymentServiceProvider.dto;

import java.io.Serializable;

public class PaymentDTO implements Serializable {

    private String sender;
    private String receiver;
    private double amount;
    private String reference;
    private int orderId;

    public PaymentDTO() {
    }

    public PaymentDTO(String sender, String receiver, double amount, String reference, int orderId) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.reference = reference;
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
