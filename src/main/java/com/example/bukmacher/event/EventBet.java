package com.example.bukmacher.event;

import java.time.LocalDateTime;

public interface EventBet {
    long getEventId();
    String getEventName();
    Integer getBetCount();
    LocalDateTime getDateTime();
}
