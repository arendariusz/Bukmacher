package com.example.bukmacher.event;

import com.example.bukmacher.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.model.IModelVisitor;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class EventController {

    private EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/event/{id}")
    public String showEvent(@PathVariable Long id, Model model) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        model.addAttribute("dateNow", LocalDateTime.now());
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            model.addAttribute("event", event);
            return "event";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/event/{id}/edit")
    public String showEventEditForm(@PathVariable Long id, Model model) {
        Optional<Event> eventOptional = eventRepository.findById(id);

        if (eventOptional.isPresent()) {
            Event eventToEdit = eventOptional.get();
            model.addAttribute("eventToEdit", eventToEdit);
            return "eventEdit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/event/{id}/edit")
    public String eventEdit(@PathVariable Long id, Event event) {
        Event event1 = eventRepository.findById(id).orElseThrow();

        event1.setHomePoints(event.getHomePoints());
        event1.setAwayPoints(event.getAwayPoints());

        if (event.getHomePoints() - event.getAwayPoints() > 0) {
            event1.setResult(Result.HOME);
        } else if (event.getHomePoints() - event.getAwayPoints() == 0) {
            event1.setResult((Result.DRAW));
        } else {
            event1.setResult(Result.AWAY);
        }
        eventRepository.save(event1);
        return "redirect:/event/" + event.getId();
    }

    @GetMapping("event/add")
    public String addEvent(Model model) {
        model.addAttribute("event", new Event());
        return "eventAdd";
    }

    @PostMapping("event/add")
    public String addEvent(@Valid @ModelAttribute Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "eventAdd";
        }
        eventRepository.save(event);
        return "redirect:/event/" + event.getId();
    }

    @GetMapping("event/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventRepository.deleteById(id);
        return "redirect:/";
    }
}
