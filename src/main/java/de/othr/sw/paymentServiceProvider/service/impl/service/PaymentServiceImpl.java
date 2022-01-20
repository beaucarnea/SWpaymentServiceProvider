package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.othr.sw.paymentServiceProvider.entity.Account;
import de.othr.sw.paymentServiceProvider.entity.Payment;
import de.othr.sw.paymentServiceProvider.entity.User;
import de.othr.sw.paymentServiceProvider.repository.AccountRepo;
import de.othr.sw.paymentServiceProvider.repository.PaymentRepo;
import de.othr.sw.paymentServiceProvider.repository.UserRepo;
import de.othr.sw.paymentServiceProvider.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

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
    public Collection<Payment> getPaymentsByThisAccountId() {
        User thisUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            return paymentRepo.findByReceiverAccount_AccountIdOrSenderAccount_AccountId(thisUser.getAccount().getAccountId(), thisUser.getAccount().getAccountId());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public Payment addPayment(Payment payment) {
        User thisUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account senderAccount = thisUser.getAccount();
        payment.setSenderAccount(senderAccount);
        payment.setSender(thisUser.getEmail());
        payment.setDate(new Date());
        if(payment.getReceiver().equals("")){
            return null;
        }
        try{
            Optional<User> receiver = userRepo.findUserByEmail(payment.getReceiver());
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
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}