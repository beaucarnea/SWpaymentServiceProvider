package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.othr.sw.paymentServiceProvider.dto.ExternalPaymentDTO;
import de.othr.sw.paymentServiceProvider.entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Scope("singleton")
public class ExternalPayments {

    private HashMap<Long, ExternalPaymentDTO> externalPayments = new HashMap<Long, ExternalPaymentDTO>();
    private HashMap<Integer, String> webAddress = new HashMap<>();
    private Long paymentCounter;

    public ExternalPayments() {
    }

    public Long getPaymentCounter() {
        return paymentCounter;
    }

    private void setPaymentCounter() {
        this.paymentCounter = this.paymentCounter+1;
    }

    public void addNewPayment(ExternalPaymentDTO payment){
        externalPayments.put(paymentCounter, payment);
    }

    public ExternalPaymentDTO getPayment(Long id){
        User thisUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ExternalPaymentDTO payment = externalPayments.get(id);
        if(payment == null){
            return null;
        }
        if(thisUser.getEmail() == payment.getSender()){
            return payment;
        } else{
            return null;
        }
    }
}
