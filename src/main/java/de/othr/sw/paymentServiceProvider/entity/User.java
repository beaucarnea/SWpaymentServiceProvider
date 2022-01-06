package de.othr.sw.paymentServiceProvider.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User{

    @Id
    private Long userId;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String eMail;
    private String phoneNumber;
    @OneToOne
    private Address address;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @ManyToOne
    private BankAccount account;
}
