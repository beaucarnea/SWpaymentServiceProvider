package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.othr.sw.paymentServiceProvider.dto.ClubDTO;
import de.othr.sw.paymentServiceProvider.entity.Raffle;
import de.othr.sw.paymentServiceProvider.repository.RaffleRepo;
import de.othr.sw.paymentServiceProvider.service.RaffleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Scope(scopeName = "singleton")
public class RaffleServiceImpl implements RaffleService {

    @Autowired
    private RestTemplate restServiceClient;

    @Autowired
    private RaffleRepo raffleRepo;

    public void testEinesRestServices() {
        ClubDTO[] clubDTO = restServiceClient.getForObject("http://im-lamport:7080/api/v1/vehicles/{kennzeichen}", ClubDTO[].class, "lksdjf");
        // Weitere Bearbeitung ...
    }

    @Override
    public Raffle addRaffle(Raffle raffle) {
        raffleRepo.save(raffle);
        return raffle;
    }
}

