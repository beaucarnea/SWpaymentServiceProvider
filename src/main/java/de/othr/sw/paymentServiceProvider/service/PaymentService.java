package de.othr.sw.paymentServiceProvider.service;

import de.othr.sw.paymentServiceProvider.entity.Payment;

import java.util.Collection;
import java.util.List;

public interface PaymentService {
    Payment getPaymentById(Long userId);
    List<Payment> getPaymentsByAccount(Long accountId);
    Collection<Payment> getPaymentsByEmail(String senderEmail, String receiverEmail);
    Collection<Payment> getPaymentsByAccountId(Long accountId);
    Payment addPayment(Payment payment);


}
