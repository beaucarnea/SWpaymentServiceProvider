package de.othr.sw.paymentServiceProvider.service.impl.service;

import antlr.PythonCharFormatter;
import de.othr.sw.paymentServiceProvider.dto.ExternalPaymentDTO;
import de.othr.sw.paymentServiceProvider.dto.PaymentStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiverService {

    @Autowired
    ExternalPayments externalPayments;

    @Autowired
    MessageSenderService messageSenderService;

    @JmsListener(destination = "sw_marius1_hauenstein_queue_thorsten")
    public void receiveMessageTest(PaymentStatusDTO paymentStatusDTO) {
        System.out.println("worked: " + paymentStatusDTO.getStatus());

    }
/*
    @JmsListener(destination = "sw_marius1_hauenstein_queue_thorsten_externalPayment")
    public void receiveMessage(ExternalPayment externalPayment) {
        System.out.println("external Payment worked " +  externalPayment);
        externalPayments.addNewPayment(externalPayment);
        messageSenderService.sendWebAddress("thorsten");
    }*/

    @JmsListener(destination = "sw_thorsten_bauer_queue_PayQueue")
    public void receiveMessage(ExternalPaymentDTO externalPayment) {
        System.out.println("external Payment worked " +  externalPayment);

        messageSenderService.sendWebAddress();
    }

    @JmsListener(destination = "sw_marius1_hauenstein_queue_thorsten_webAddress")
    public void receiveMessage2(String webAddress) {
        System.out.println("worked: " + webAddress);

    }


}
