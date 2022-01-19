package de.othr.sw.paymentServiceProvider.entity;

import de.othr.sw.paymentServiceProvider.entity.util.SingleIdEntity;

import javax.persistence.*;

@Entity
public class Raffle extends SingleIdEntity<Long> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long raffleId;
    private double amount;
    private String winner;

    public Raffle(){

    }

    public Long getRaffleId() {
        return raffleId;
    }

    public void setRaffleId(Long raffleId) {
        this.raffleId = raffleId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    @Override
    public Long getID() {
        return this.raffleId;
    }

}
