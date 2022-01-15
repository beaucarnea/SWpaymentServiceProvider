package de.othr.sw.paymentServiceProvider.dto;

public class PaymentStatus {
    private String status;
    private String custormerEmail;
    private String storeEmail;
    private Integer orderId;

    public PaymentStatus(String status, String custormerEmail, String storeEmail, Integer orderId) {
        this.status = status;
        this.custormerEmail = custormerEmail;
        this.storeEmail = storeEmail;
        this.orderId = orderId;
    }
}
