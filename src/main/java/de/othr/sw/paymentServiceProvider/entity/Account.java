package de.othr.sw.paymentServiceProvider.entity;

import de.othr.sw.paymentServiceProvider.entity.util.SingleIdEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Account extends SingleIdEntity<Long> {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long accountId;
//  private User user;
    private String accountNumber;
    @OneToMany(mappedBy="sender", fetch = FetchType.EAGER)
    private List<Payment> payments;

    public Account(){

    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<Payment> getPayments() {
        return Collections.unmodifiableList(payments);
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public void addPayment(Payment payment){
        this.payments.add(payment);
    }

    public void removePayment(Payment payment){
        payments.remove(payment);
    }

    @Override
    public Long getID() {
        return this.accountId;
    }
}
