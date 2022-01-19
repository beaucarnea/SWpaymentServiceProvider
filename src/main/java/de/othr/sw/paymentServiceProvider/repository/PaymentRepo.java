package de.othr.sw.paymentServiceProvider.repository;

import de.othr.sw.paymentServiceProvider.entity.Account;
import de.othr.sw.paymentServiceProvider.entity.Payment;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;

@Repository
@Scope(scopeName = "singleton")
public interface PaymentRepo extends PagingAndSortingRepository<Payment, Long> {
    Collection<Payment> findBySenderContaining(Long sender);
    //Collection<Payment> findBySenderContaining(Long sender, PageRequest pageable);
    Collection<Payment> findBySenderAccount(Account account);
    Collection<Payment> findByReceiverAccount_AccountIdOrSenderAccount_AccountId(Long receiverId, Long senderId);
    Collection<Payment> deleteAllByReceiverAccount(Account account);
    Collection<Payment> deleteAllBySenderAccount(Account accout);
}
