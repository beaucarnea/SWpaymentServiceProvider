package de.othr.sw.paymentServiceProvider.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class BankAccount {

    @Id
    private Long accountId;
    @OneToMany(mappedBy = "account")
    private Collection<User> users;
    private String accountNumber;
    @OneToMany
    private Collection<Payment> payments;
}
