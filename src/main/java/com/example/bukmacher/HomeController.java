package com.example.bukmacher;

import com.example.bukmacher.event.Event;
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
        model.addAttribute("allEvents", eventList);
        return "home";
    }

    @GetMapping("/eventsToBet")
    public String allEvents(Model model) {
        List<Event> eventList = eventRepository.findEventsByDateTimeIsAfter(LocalDateTime.now());
        model.addAttribute("allEvents", eventList);
        return "home";
    }

}
