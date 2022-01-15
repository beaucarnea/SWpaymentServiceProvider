package de.othr.sw.paymentServiceProvider.repository;

import de.othr.sw.paymentServiceProvider.entity.Account;
import de.othr.sw.paymentServiceProvider.entity.Payment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;

@Repository
public interface PaymentRepo extends PagingAndSortingRepository<Payment, Long> {
    Collection<Payment> findBySenderContaining(Long sender);
    //Collection<Payment> findBySenderContaining(Long sender, PageRequest pageable);
    Collection<Payment> findBySenderAccount(Account account);
}
