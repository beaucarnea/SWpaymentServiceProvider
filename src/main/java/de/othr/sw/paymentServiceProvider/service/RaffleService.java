package de.othr.sw.paymentServiceProvider.service;

import de.othr.sw.paymentServiceProvider.entity.Raffle;

public interface RaffleService {
    Raffle newRaffle(Raffle raffle);
    Raffle addRaffle(Raffle raffle);
}
