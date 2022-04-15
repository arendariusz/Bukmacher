package com.example.bukmacher.bet;

import com.example.bukmacher.Result;

import java.math.BigDecimal;

public class BetUtility {
    public static BigDecimal countReward(Bet bet) {
        BigDecimal reward = new BigDecimal(0);
        if (bet.getType() == Result.HOME) {
            reward = bet.getStake().multiply(BigDecimal.valueOf(bet.getEvent().getHomeOdd()));
        } else if (bet.getType() == Result.DRAW) {
            reward = bet.getStake().multiply(BigDecimal.valueOf(bet.getEvent().getDrawOdd()));
        } else {
            reward = bet.getStake().multiply(BigDecimal.valueOf(bet.getEvent().getAwayOdd()));
        }
        return reward;
    }
}
