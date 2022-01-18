package de.othr.sw.paymentServiceProvider.service;

import de.othr.sw.paymentServiceProvider.dto.PaymentDTO;
import de.othr.sw.paymentServiceProvider.dto.PaymentStatusDTO;

public interface PaymentApiService {
    public void sendPaymentStatus(PaymentStatusDTO paymentStatusDTO);
    public void receiveMessage(PaymentDTO newPayment);
}
