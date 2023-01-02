package com.example.bukmacher;

import com.example.bukmacher.event.Event;
import com.example.bukmacher.event.EventBet;
import com.example.bukmacher.event.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HomeController {

    private EventService eventService;

    public HomeController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Event> eventList = eventService.findAll();
        model.addAttribute("eventList", eventList);
        return "home";
    }

    @GetMapping("/eventsToBet")
    public String eventsToBet(Model model) {
        List<Event> eventList = eventService.findEventsByDateTimeIsAfter(LocalDateTime.now());
        model.addAttribute("eventList", eventList);
        return "home";
    }

    @GetMapping("/popularEvents")
    public String allEvents(Model model) {
        List<EventBet> eventList = eventService.findMostPopularEvents();
        model.addAttribute("eventList", eventList);
        return "mostPopularBets";
    }

}
