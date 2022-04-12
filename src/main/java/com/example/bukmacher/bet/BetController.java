package com.example.bukmacher.bet;

import com.example.bukmacher.event.Event;
import com.example.bukmacher.event.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class BetController {

    private EventRepository eventRepository;
    private BetRepository betRepository;

    public BetController(EventRepository eventRepository, BetRepository betRepository) {
        this.eventRepository = eventRepository;
        this.betRepository = betRepository;
    }

    @GetMapping("/bet/{id}")
    public String showEventEditForm(@PathVariable Long id, Model model) {
        Optional<Event> eventOptional = eventRepository.findById(id);

        model.addAttribute("bet", new Bet());
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            model.addAttribute("event", event);
            return "bet";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/bet/{id}")
    public String addBet(@PathVariable Long id, Bet bet) {
        Bet bet1 = new Bet();
        bet1.setStake(bet.getStake());
        bet1.setType(bet.getType());
        bet1.setEvent(eventRepository.getById(id));
        betRepository.save(bet1);
        return "redirect:/coupon/" + bet1.getId();
    }

    @GetMapping("/checkYourCoupon")
    public String checkYourBet(Model model) {
        model.addAttribute("checkBet", new Bet());
        return "checkYourCoupon";
    }

    @PostMapping("/checkYourCoupon")
    public String checkBetById(Bet checkBet, Model model) {
        Bet bet1 = betRepository.findById(checkBet.getId()).orElseThrow();
        BigDecimal reward = new BigDecimal(0);
        if (bet1.getType() == bet1.getEvent().getResult()) {
            reward = BetUtility.countReward(bet1);
        }
        model.addAttribute("reward", reward);

        return "redirect:/coupon/" + bet1.getId();
    }

    @GetMapping("/coupon/{id}")
    public String yourCoupon(@PathVariable Long id, Model model) {
        Optional<Bet> coupon = betRepository.findById(id);
        Bet bet = new Bet();
        BigDecimal reward = new BigDecimal(0);
        if (coupon.isPresent()) {
            bet = coupon.get();
            if (bet.getType() == bet.getEvent().getResult()) {
                reward = BetUtility.countReward(bet);
            }
        }
        model.addAttribute("dateNow", LocalDateTime.now());
        model.addAttribute("reward", reward);
        model.addAttribute("yourCoupon", bet);
        return "coupon";
    }

}
