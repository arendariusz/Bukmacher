package com.example.bukmacher.bet;

import com.example.bukmacher.event.Event;
import com.example.bukmacher.event.EventService;
import com.example.bukmacher.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class BetController {

    private EventService eventService;
    private BetService betService;
    private UserService userService;

    public BetController(EventService eventService, BetService betService, UserService userService) {
        this.eventService = eventService;
        this.betService = betService;
        this.userService = userService;
    }

    @GetMapping("/bet/{id}")
    public String showEventEditForm(@PathVariable Long id, Model model, Principal principal) {
        Event event1 = eventService.getEventById(id);

        model.addAttribute("bet", new Bet());
        model.addAttribute("event", event1);

        return "bet";
    }

    @PostMapping("/bet/{id}")
    public String addBet(@PathVariable Long id,Bet bet, Principal principal) {
        bet.setUser(userService.findUserByUsername(principal.getName()).orElseThrow());
        bet.setEvent(eventService.getEventById(id));
        betService.saveNewBet(bet);
        return "redirect:/coupon/" + bet.getId();
    }

    @GetMapping("/checkYourCoupon")
    public String checkYourBet(Model model) {
        model.addAttribute("checkBet", new Bet());
        return "checkYourCoupon";
    }

    @PostMapping("/checkYourCoupon")
    public String checkBetById(Bet checkBet, Model model) {
        Bet bet1 = betService.findById(checkBet.getId()).orElseThrow();
        BigDecimal reward = new BigDecimal(0);
        if (bet1.getType() == bet1.getEvent().getResult()) {
            reward = BetUtility.countReward(bet1);
        }
        model.addAttribute("reward", reward);

        return "redirect:/coupon/" + bet1.getId();
    }

    @GetMapping("/coupon/{id}")
    public String yourCoupon(@PathVariable Long id, Model model) {
        Optional<Bet> coupon = betService.findById(id);
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
