package com.example.bukmacher.bet;

import com.example.bukmacher.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Long> {

}
