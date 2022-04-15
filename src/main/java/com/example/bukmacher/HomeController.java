package com.example.bukmacher;

import com.example.bukmacher.event.Event;
import com.example.bukmacher.event.EventBet;
import com.example.bukmacher.event.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class HomeController {

    private EventRepository eventRepository;

    public HomeController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Event> eventList = eventRepository.findAll();
        model.addAttribute("eventList", eventList);
        return "home";
    }

    @GetMapping("/eventsToBet")
    public String eventsToBet(Model model) {
        List<Event> eventList = eventRepository.findEventsByDateTimeIsAfter(LocalDateTime.now());
        model.addAttribute("eventList", eventList);
        return "home";
    }

    @GetMapping("/popularEvents")
    public String allEvents(Model model) {
        List<EventBet> eventList = eventRepository.findMostPopularEvents();
        model.addAttribute("eventList", eventList);
        return "mostPopularBets";
    }

}
