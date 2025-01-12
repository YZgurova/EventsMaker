package com.EventsMaker.v1.api.rest;

import com.EventsMaker.v1.auth.MyUser;
import com.EventsMaker.v1.models.Event;
import com.EventsMaker.v1.models.EventInput;
import com.EventsMaker.v1.services.EventsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/events")
@SecurityRequirement(name = "bearerAuth")
public class EventsController
{
    @Resource
    private EventsService eventsService;

    @PostMapping
    public Event createEvent(@RequestBody EventInput event, @AuthenticationPrincipal MyUser user) {
        return eventsService.createEvent(event.title, event.description, user.id, event.price);
    }

    @GetMapping(value = "/all")
    public List<Event> listAllEvents() {
        return new ArrayList<>(eventsService.listAllEvents());
    }

    @GetMapping(value = "/user/list")
    public List<Event> listOwnEvents(@AuthenticationPrincipal MyUser user) {
        return new ArrayList<>(eventsService.listOwnEvents(user.id));
    }

    @GetMapping(value = "/user/list/booked")
    public List<Event> listBookedEvents(@AuthenticationPrincipal MyUser user) {
        return new ArrayList<>(eventsService.listBookedEvents(user.id));
    }

    @GetMapping(value = "/{eventId}")
    public Event getEvent(@PathVariable Integer eventId) {
        return eventsService.getEvent(eventId);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEvent(@PathVariable Integer id) {
        eventsService.deleteEvent(id);
    }
}
