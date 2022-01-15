package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.othr.sw.paymentServiceProvider.dto.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;

public class MessageSenderService {
    @Autowired
    private JmsTemplate jmsTemplate;
    public void getResourceType(@PathVariable("typeid") String id) {
        jmsTemplate.convertAndSend("Queue-Name", new PaymentStatus("PAYED", "thorsten@gmx.de", "marius@gmx.de", 23443));
    }
}
