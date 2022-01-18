package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.othr.sw.paymentServiceProvider.dto.PaymentDTO;
import de.othr.sw.paymentServiceProvider.dto.PaymentStatusDTO;
import de.othr.sw.paymentServiceProvider.entity.Payment;
import de.othr.sw.paymentServiceProvider.entity.User;
import de.othr.sw.paymentServiceProvider.repository.PaymentRepo;
import de.othr.sw.paymentServiceProvider.repository.UserRepo;
import de.othr.sw.paymentServiceProvider.service.PaymentApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public class PaymentApiServiceBeverageStore implements PaymentApiService {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PaymentRepo paymentRepo;

    public void sendPaymentStatus(PaymentStatusDTO paymentStatusDTO) {
        System.out.println("sendPaymentStatus");
        jmsTemplate.convertAndSend("sw_marius1_hauenstein_queue_thorsten_paymentStatus", paymentStatusDTO);
    }

    @JmsListener(destination = "sw_thorsten_bauer_queue_PayQueue")
    public void receiveMessage(PaymentDTO newPayment) {
        PaymentStatusDTO paymentStatusDTO = new PaymentStatusDTO(false, newPayment.getOrderId());
        Payment payment = new Payment();
        payment.setReceiver(newPayment.getReceiver());
        payment.setSender(newPayment.getSender());
        payment.setReference(newPayment.getReference());
        payment.setAmount(newPayment.getAmount());
        Optional<User> receiverAccount = userRepo.findUserByEmailContaining(newPayment.getReceiver());
        if(receiverAccount.isPresent()){
            System.out.println("receiverAccount");
            payment.setReceiverAccount(receiverAccount.get().getAccount());
        }else{
            System.out.println("no receiverAccount");
            this.sendPaymentStatus(paymentStatusDTO);
            return;
        }
        Optional<User> senderAccount = userRepo.findUserByEmailContaining(newPayment.getSender());
        if(senderAccount.isPresent()){
            payment.setSenderAccount(senderAccount.get().getAccount());
        }else{
            this.sendPaymentStatus(paymentStatusDTO);
            return;
        }
        System.out.println(LocalDate.now());
        payment.setDate(new Date());
        try{
            paymentRepo.save(payment);
        }catch (Exception e){
            this.sendPaymentStatus(paymentStatusDTO);
        }
        paymentStatusDTO.setStatus(true);
        this.sendPaymentStatus(paymentStatusDTO);
    }
}
