package de.othr.sw.paymentServiceProvider.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Raffle {
    @Id
    private Long raffleId;
    @ManyToOne
    private BankAccount sender;
    @ManyToOne
    private BankAccount receiver;
    private int amount;
    private int participants;
    private Long winner;
    @OneToOne
    private Payment payment;
}
