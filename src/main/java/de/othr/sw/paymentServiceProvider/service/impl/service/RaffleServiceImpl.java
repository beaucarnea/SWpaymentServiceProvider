package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.oth.managementsys.entity.MemberDTO;
import de.othr.sw.paymentServiceProvider.dto.ClubDTO;
import de.othr.sw.paymentServiceProvider.entity.Account;
import de.othr.sw.paymentServiceProvider.entity.Payment;
import de.othr.sw.paymentServiceProvider.entity.Raffle;
import de.othr.sw.paymentServiceProvider.entity.User;
import de.othr.sw.paymentServiceProvider.repository.RaffleRepo;
import de.othr.sw.paymentServiceProvider.repository.UserRepo;
import de.othr.sw.paymentServiceProvider.service.PaymentService;
import de.othr.sw.paymentServiceProvider.service.RaffleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

@Service
@Scope(scopeName = "singleton")
public class RaffleServiceImpl implements RaffleService {

    @Autowired
    private RestTemplate restServiceClient;

    @Autowired
    private RaffleRepo raffleRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PaymentService paymentService;

    @Transactional
    public Raffle newRaffle(Raffle raffle) {
        MemberDTO[] memberDTOs;
        try {
            System.out.println("get Members");
            memberDTOs = restServiceClient.getForObject("http://172.16.37.98:8080/api/v1/members", MemberDTO[].class);
        }catch (Exception e){
            raffle = null;
            return raffle;
        }
        Random rand = new Random();
        int n = rand.nextInt(memberDTOs.length);
        String receiver = memberDTOs[n].getMail();
        raffle.setWinner(receiver);
        System.out.println("receiver: " + receiver);

        Optional<User> user = userRepo.findUserByEmailContaining(receiver);
        if(!user.isPresent()){
            // send Email: You've won!!! Please register at PaymentServiceProvider :)
        }
        Payment payment = new Payment();
        payment.setReceiver(user.get().getEmail());
        payment.setAmount(raffle.getAmount());
        payment.setReference("Raffle");
        paymentService.addPayment(payment);
        raffleRepo.save(raffle);

    return raffle;
    }

    @Override
    public Raffle addRaffle(Raffle raffle) {
        raffleRepo.save(raffle);
        return raffle;
    }
}

