package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.othr.sw.paymentServiceProvider.entity.Payment;
import de.othr.sw.paymentServiceProvider.repository.PaymentRepo;
import de.othr.sw.paymentServiceProvider.service.PaymentService;
import de.othr.sw.paymentServiceProvider.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo PaymentRepo;

    @Override
    public Payment getPaymentsById (Long paymentId) {
        return PaymentRepo.findById(paymentId).orElseThrow(
                () -> new ServiceException("Payments with " + paymentId + " not found!")
        );
    }

    @Override
    public List<Payment> getPaymentsByAccount(Long accountId) {
        return (List<Payment>) PaymentRepo.findAll(); // ToDo: search for accountId
    }

    @Override
    public void addPayment() {

    }
}