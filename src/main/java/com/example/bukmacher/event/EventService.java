package com.example.bukmacher.event;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<Event> findEventsByDateTimeIsAfter(LocalDateTime now) {
        return eventRepository.findEventsByDateTimeIsAfter(now);
    }

    public List<EventBet> findMostPopularEvents() {
        return eventRepository.findMostPopularEvents();
    }

    public Event getEventById(Long id) {
        return eventRepository.getById(id);
    }

    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    public void save(Event event1) {
        eventRepository.save(event1);
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }
}
