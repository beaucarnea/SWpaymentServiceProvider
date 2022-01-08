package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.othr.sw.paymentServiceProvider.entity.Payment;
import de.othr.sw.paymentServiceProvider.repository.PaymentRepo;
import de.othr.sw.paymentServiceProvider.service.PaymentService;
import de.othr.sw.paymentServiceProvider.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Override
    public Payment getPaymentById (Long paymentId) {
        return paymentRepo.findById(paymentId).orElseThrow(
                () -> new ServiceException("Payment with " + paymentId + " not found!")
        );
    }

    @Override
    public List<Payment> getPaymentsByAccount(Long accountId) {
        return (List<Payment>) paymentRepo.findAll(); // ToDo: search for accountId
    }

    @Override
    public Collection<Payment> getPaymentsByEmail(String senderEmail, String receiverEmail) {

        //return StreamSupport.stream(paymentRepo.findAll().spliterator(), false).collect(Collectors.toList());
        return null;
    }

    @Override
    public Collection<Payment> getPaymentsByAccountId(Long accountId) {
        return paymentRepo.findBySenderContaining(accountId);
    }

    @Override
    public void addPayment() {

    }
}