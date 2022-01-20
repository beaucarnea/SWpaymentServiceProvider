package de.othr.sw.paymentServiceProvider.service;

import de.othr.sw.paymentServiceProvider.entity.Payment;

import java.util.Collection;
import java.util.List;

public interface PaymentService {
    Collection<Payment> getPaymentsByThisAccountId();
    Payment addPayment(Payment payment);

}
