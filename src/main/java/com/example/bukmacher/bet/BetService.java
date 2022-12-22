package com.example.bukmacher.bet;

public class BetService {

    private BetRepository betRepository;

    public BetRepository getBetRepository() {
        return betRepository;
    }

    public void setBetRepository(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public void saveNewBet(Bet bet) {
        Bet betToSave = new Bet();
        betToSave.setType(bet.getType());
    }
}
