package com.example.bukmacher.bet;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BetService {

    private BetRepository betRepository;

    public BetService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public void saveNewBet(Bet bet) {
        Bet betToSave = new Bet();
        betToSave.setType(bet.getType());
        betToSave.setEvent(bet.getEvent());
        betToSave.setStake(bet.getStake());
        betToSave.setUser(bet.getUser());
        betRepository.save(betToSave);
    }

    public Optional<Bet> findById(Long id) {
        return betRepository.findById(id);
    }
}
