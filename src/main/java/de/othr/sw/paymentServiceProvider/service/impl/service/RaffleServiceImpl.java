package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.othr.sw.paymentServiceProvider.dto.ClubDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RaffleServiceImpl {

        @Autowired
        private RestTemplate restServiceClient;

        public void testEinesRestServices() {
            ClubDTO[] clubDTO = restServiceClient.getForObject("http://im-lamport:7080/api/v1/vehicles/{kennzeichen}", ClubDTO[].class, "lksdjf");
            // Weitere Bearbeitung ...
    }
}

