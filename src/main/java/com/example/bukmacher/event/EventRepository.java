package com.example.bukmacher.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findEventsByDateTimeIsAfter(LocalDateTime dateTime);

    @Query("SELECT e.id as eventId, e.name as eventName, e.dateTime as dateTime," +
            "COUNT(b) as betCount FROM Event e JOIN e.betList b WHERE e.dateTime > CURRENT_TIMESTAMP GROUP BY eventId ORDER BY betCount DESC")
    List<EventBet> findMostPopularEvents();
}
