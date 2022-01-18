package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.oth.managementsys.entity.Member;
import de.othr.sw.paymentServiceProvider.dto.ClubDTO;
import de.othr.sw.paymentServiceProvider.entity.Raffle;
import de.othr.sw.paymentServiceProvider.entity.User;
import de.othr.sw.paymentServiceProvider.repository.RaffleRepo;
import de.othr.sw.paymentServiceProvider.repository.UserRepo;
import de.othr.sw.paymentServiceProvider.service.RaffleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
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

    public Raffle newRaffle(Raffle raffle) {
        System.out.println(restServiceClient.getForObject("http://172.16.37.98:8080/api/v1/members", Member[].class));
        //System.out.println(membertestDTO);
        Random rand = new Random();
        //int n = rand.nextInt(memberDTO.length);
        int n = 2;
        ArrayList<String> memberDTO = new ArrayList<String>();
        memberDTO.add("thorsten@clubshop.de");
        memberDTO.add("ramodm@hi.de");
        String receiver = memberDTO.get(n);

        Optional<User> user = userRepo.findUserByEmailContaining(receiver);
        if(!user.isPresent()){
            // send Email: You've won!!! Please register at PaymentServiceProvider :)
        }
        raffle.setReceiver(user.get().getAccount());

    return raffle;
    }

    @Override
    public Raffle addRaffle(Raffle raffle) {
        raffleRepo.save(raffle);
        return raffle;
    }
}

