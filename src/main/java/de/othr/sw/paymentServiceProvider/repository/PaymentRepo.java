package de.othr.sw.paymentServiceProvider.repository;

import de.othr.sw.paymentServiceProvider.entity.Payment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends PagingAndSortingRepository<Payment, Long> {

}
