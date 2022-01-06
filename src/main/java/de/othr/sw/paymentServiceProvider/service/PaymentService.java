package de.othr.sw.paymentServiceProvider.service;

import de.othr.sw.paymentServiceProvider.entity.Payment;

import java.util.List;

public interface PaymentService {
    Payment getPaymentsById(Long userId);
    List<Payment> getPaymentsByAccount(Long accountId);
    void addPayment();


}
