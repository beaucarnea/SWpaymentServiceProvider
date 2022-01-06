package de.othr.sw.paymentServiceProvider.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Payment {
    @Id
    private Long paymentId;
    private String sender;
    private String receiver;
    private String amount; //ToDo: datatype?
    private String reference;
    private Date date;
}
