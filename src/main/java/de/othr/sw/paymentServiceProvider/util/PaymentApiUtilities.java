package de.othr.sw.paymentServiceProvider.util;

import de.othr.sw.paymentServiceProvider.dto.PaymentDTO;
import de.othr.sw.paymentServiceProvider.entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Scope("singleton")
public class PaymentApiUtilities {

    private HashMap<Long, PaymentDTO> externalPayments = new HashMap<Long, PaymentDTO>();
    private HashMap<Integer, String> webAddress = new HashMap<>();
    private Long paymentCounter;

    public PaymentApiUtilities() {
    }

    public Long getPaymentCounter() {
        return paymentCounter;
    }

    private void setPaymentCounter() {
        this.paymentCounter = this.paymentCounter+1;
    }

    public void addNewPayment(PaymentDTO payment){
        externalPayments.put(paymentCounter, payment);
    }

    public PaymentDTO getPayment(Long id){
        User thisUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PaymentDTO payment = externalPayments.get(id);
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
