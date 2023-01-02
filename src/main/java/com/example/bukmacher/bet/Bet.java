package com.example.bukmacher.bet;

import com.example.bukmacher.Result;
import com.example.bukmacher.event.Event;
import com.example.bukmacher.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal stake;

    @Enumerated(EnumType.STRING)
    private Result type;

    @ManyToOne
    private Event event;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getStake() {
        return stake;
    }

    public void setStake(BigDecimal stake) {
        this.stake = stake;
    }

    public Result getType() {
        return type;
    }

    public void setType(Result type) {
        this.type = type;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
