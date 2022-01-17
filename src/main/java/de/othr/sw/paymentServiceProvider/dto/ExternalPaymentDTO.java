package de.othr.sw.paymentServiceProvider.dto;

import de.othr.sw.paymentServiceProvider.entity.Account;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

public class ExternalPaymentDTO implements Serializable {

    private String sender;
    private String receiver;
    private Double amount;
    private String reference;
    private Integer orderId;

    public ExternalPaymentDTO() {
    }

    public ExternalPaymentDTO(String sender, String receiver, Double amount, String reference, Integer orderId) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.reference = reference;
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        orderId = orderId;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
