package com.example.bukmacher.event;

import com.example.bukmacher.bet.Bet;
import com.example.bukmacher.Result;
import com.example.bukmacher.config.EventValidationGroups;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(groups = EventValidationGroups.Create.class)
    private String name;
    @NotNull(groups = EventValidationGroups.Create.class)
    @FutureOrPresent(groups = EventValidationGroups.Create.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    @NotNull(groups = EventValidationGroups.Create.class)
    @Min(value = 1, groups = EventValidationGroups.Create.class)
    private double homeOdd;
    @NotNull
    @Min(value = 1, groups = EventValidationGroups.Create.class)
    private double drawOdd;
    @NotNull
    @Min(value = 1, groups = EventValidationGroups.Create.class)
    private double awayOdd;
    @Min(value = 0, groups = EventValidationGroups.Update.class)
    private Integer homePoints;
    @Min(value = 0,groups = EventValidationGroups.Create.class)
    private Integer awayPoints;

    @Enumerated(EnumType.STRING)
    private Result result;

    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE)
    private List<Bet> betList;

    public String resultToString() {
        return homePoints + ":" + awayPoints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getHomeOdd() {
        return homeOdd;
    }

    public void setHomeOdd(double homeOdd) {
        this.homeOdd = homeOdd;
    }

    public double getDrawOdd() {
        return drawOdd;
    }

    public void setDrawOdd(double drawOdd) {
        this.drawOdd = drawOdd;
    }

    public double getAwayOdd() {
        return awayOdd;
    }

    public void setAwayOdd(double awayOdd) {
        this.awayOdd = awayOdd;
    }

    public List<Bet> getBetList() {
        return betList;
    }

    public void setBetList(List<Bet> betList) {
        this.betList = betList;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Integer getHomePoints() {
        return homePoints;
    }

    public void setHomePoints(Integer homePoints) {
        this.homePoints = homePoints;
    }

    public Integer getAwayPoints() {
        return awayPoints;
    }

    public void setAwayPoints(Integer awayPoints) {
        this.awayPoints = awayPoints;
    }
}
