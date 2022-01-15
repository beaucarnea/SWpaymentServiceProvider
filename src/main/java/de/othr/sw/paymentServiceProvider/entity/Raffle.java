package de.othr.sw.paymentServiceProvider.entity;

import de.othr.sw.paymentServiceProvider.entity.util.SingleIdEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Raffle extends SingleIdEntity<Long> {
    @Id
    private Long raffleId;
    @ManyToOne
    private Account sender;
    @ManyToOne
    private Account receiver;
    private int amount;
    private int participants;
    private Long winner;
    @OneToOne
    private Payment payment;

    public Raffle(){

    }

    @Override
    public Long getID() {
        return this.raffleId;
    }
}
