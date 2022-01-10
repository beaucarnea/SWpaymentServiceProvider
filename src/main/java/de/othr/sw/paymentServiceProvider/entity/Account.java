package de.othr.sw.paymentServiceProvider.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long accountId;
//    private User user;
    private String accountNumber;
    @OneToMany
    private Collection<Payment> payments;
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

    public Collection<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Collection<Payment> payments) {
        this.payments = payments;
    }
}
