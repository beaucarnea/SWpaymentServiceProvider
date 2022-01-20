package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.othr.sw.paymentServiceProvider.dto.PaymentDTO;
import de.othr.sw.paymentServiceProvider.dto.PaymentStatusDTO;
import de.othr.sw.paymentServiceProvider.entity.Payment;
import de.othr.sw.paymentServiceProvider.entity.User;
import de.othr.sw.paymentServiceProvider.repository.PaymentRepo;
import de.othr.sw.paymentServiceProvider.repository.UserRepo;
import de.othr.sw.paymentServiceProvider.service.PaymentApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Scope(scopeName = "singleton")
class PaymentApiServiceClubShop implements PaymentApiService {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PaymentRepo paymentRepo;

    public void sendPaymentStatus(PaymentStatusDTO paymentStatusDTO) {
        try{
            jmsTemplate.convertAndSend("sw_marius1_hauenstein_queue_thorsten_paymentStatus", paymentStatusDTO);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @JmsListener(destination = "sw_thorsten_bauer_queue_PayQueue")
    public void receiveMessage(PaymentDTO newPayment) {
        PaymentStatusDTO paymentStatusDTO = new PaymentStatusDTO(false, newPayment.getOrderId());
        Payment payment = new Payment();
        payment.setReceiver(newPayment.getReceiver());
        payment.setSender(newPayment.getSender());
        payment.setReference(newPayment.getReference());
        payment.setAmount(newPayment.getAmount());
        try{
            Optional<User> receiverAccount = userRepo.findUserByEmail(newPayment.getReceiver());
            if(receiverAccount.isPresent()){
                payment.setReceiverAccount(receiverAccount.get().getAccount());
            }else{
                this.sendPaymentStatus(paymentStatusDTO);
                return;
            }
            Optional<User> senderAccount = userRepo.findUserByEmail(newPayment.getSender());
            if(senderAccount.isPresent()){
                payment.setSenderAccount(senderAccount.get().getAccount());
            }else{
                this.sendPaymentStatus(paymentStatusDTO);
                return;
            }
            payment.setDate(new Date());
            paymentRepo.save(payment);
            this.sendPaymentStatus(paymentStatusDTO);
        }catch(Exception e){
            e.printStackTrace();
            this.sendPaymentStatus(paymentStatusDTO);
        }

        paymentStatusDTO.setStatus(true);
        this.sendPaymentStatus(paymentStatusDTO);
    }
}
