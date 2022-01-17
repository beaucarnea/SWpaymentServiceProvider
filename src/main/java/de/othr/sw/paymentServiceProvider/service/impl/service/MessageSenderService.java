package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.othr.sw.paymentServiceProvider.dto.ExternalPaymentDTO;
import de.othr.sw.paymentServiceProvider.dto.PaymentStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendPaymentStatus() {
        jmsTemplate.convertAndSend("sw_marius1_hauenstein_queue_thorsten_paymentStatus", new PaymentStatusDTO(true, 23443));
    }

    public void sendExternalPayment() {
        //Destination queue = new ActiveMQQueue("")
        //jmsTemplate.setDefaultDestination("sw_marius1_hauenstein_queue_thorsten_payment");
        jmsTemplate.convertAndSend("sw_marius1_hauenstein_queue_thorsten_externalPayment", new ExternalPaymentDTO("mar@gmail.com", "tho@gmail.com", 32.0, "Money Money Money!", 2));
    }
/*    public void sendWebAddress(String receiver) {
        //Destination queue = new ActiveMQQueue("")
        //jmsTemplate.setDefaultDestination("sw_marius1_hauenstein_queue_thorsten_payment");
        if(receiver == "thorsten"){
            jmsTemplate.convertAndSend("sw_marius1_hauenstein_queue_thorsten_webAddress", "www.google.de");
        }
    }*/
    public void sendWebAddress() {
        //Destination queue = new ActiveMQQueue("")
        //jmsTemplate.setDefaultDestination("sw_marius1_hauenstein_queue_thorsten_payment");

        jmsTemplate.convertAndSend("sw_marius1_hauenstein_queue_thorsten_paymentStatus", "läuft");

    }
}
