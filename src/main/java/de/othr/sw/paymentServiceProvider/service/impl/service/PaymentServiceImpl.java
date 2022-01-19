package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.othr.sw.paymentServiceProvider.entity.Account;
import de.othr.sw.paymentServiceProvider.entity.Payment;
import de.othr.sw.paymentServiceProvider.entity.User;
import de.othr.sw.paymentServiceProvider.repository.AccountRepo;
import de.othr.sw.paymentServiceProvider.repository.PaymentRepo;
import de.othr.sw.paymentServiceProvider.repository.UserRepo;
import de.othr.sw.paymentServiceProvider.service.PaymentService;
import de.othr.sw.paymentServiceProvider.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.Transactional.TxType;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Scope(scopeName = "singleton")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Payment getPaymentById (Long paymentId) {
        return paymentRepo.findById(paymentId).orElseThrow(
                () -> new ServiceException("Payment with " + paymentId + " not found!")
        );
    }

    @Override
    public List<Payment> getPaymentsByAccount(Long accountId) {
        return (List<Payment>) paymentRepo.findAll(); // ToDo: search for accountId
    }

    @Override
    public Collection<Payment> getPaymentsByEmail(String senderEmail, String receiverEmail) {

        //return StreamSupport.stream(paymentRepo.findAll().spliterator(), false).collect(Collectors.toList());
        return null;
    }

    @Override
    public Collection<Payment> getPaymentsByThisAccountId() {
        //return paymentRepo.findBySenderContaining(accountId, PageRequest.of(1, 20, Sort.by("date").descending()));
        User thisUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("this User: " + thisUser.toString());
        return paymentRepo.findByReceiverAccount_AccountIdOrSenderAccount_AccountId(thisUser.getAccount().getAccountId(), thisUser.getAccount().getAccountId());
    }

    @Override
    public Collection<Payment> getPaymentsByAccount() {

        return null;
    }

    @Override
    @Transactional
    public Payment addPayment(Payment payment) {
        User thisUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account senderAccount = thisUser.getAccount();
        payment.setSenderAccount(senderAccount);
        payment.setSender(thisUser.getEmail());
        payment.setDate(new Date());
        System.out.println("Receiver: " + payment.getReceiver());
        if(payment.getReceiver() == ""){
            return null;
        }
        Optional<User> receiver = userRepo.findUserByEmailContaining(payment.getReceiver());
        Account receiverAccount;
        if(receiver.isPresent()){
            User receiverUser = receiver.get();
            receiverAccount = receiverUser.getAccount();
        }else{
            return null;
        }
        payment.setReceiverAccount(receiverAccount);
        Payment savedPayment = paymentRepo.save(payment);
        senderAccount.addPayment(savedPayment);
        accountRepo.save(senderAccount);

        return savedPayment;
    }
}