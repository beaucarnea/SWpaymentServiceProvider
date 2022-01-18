package de.othr.sw.paymentServiceProvider.dto;

import java.io.Serializable;

public class PaymentStatusDTO implements Serializable {
    private Boolean status;
    private Integer orderId;

    public PaymentStatusDTO() {
    }

    public PaymentStatusDTO(Boolean status, int orderId) {
        this.status = status;
        this.orderId = orderId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
