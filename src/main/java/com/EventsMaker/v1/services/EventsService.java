package com.EventsMaker.v1.services;

import com.EventsMaker.v1.models.Event;
import com.EventsMaker.v1.repositories.EventsRepository;
import com.EventsMaker.v1.repositories.entities.EventEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventsService
{
    private final EventsRepository repository;

    public EventsService(EventsRepository repository) {
        this.repository = repository;
    }

    public Event createEvent(String title, String description, int authorId, BigDecimal price) {
        return Mappers.fromEventEntity(repository.createEvent(title, description, authorId, price));
    }

    public Event addBookedEvent(int userId, int eventId) {
        return Mappers.fromEventEntity(repository.addBookedEvent(userId, eventId));
    }

    public List<Event> listAllEvents() {
        return repository.getAllEvents()
                         .stream()
                         .map(Mappers::fromEventEntity)
                         .collect(Collectors.toList());
    }

    public List<Event> listOwnEvents(int userId) {
        return repository.listOwnEvents((userId))
                         .stream()
                         .map(Mappers::fromEventEntity)
                         .collect(Collectors.toList());
    }

    public List<Event> listBookedEvents(int userId) {
        return repository.listBookedEvents((userId))
                         .stream()
                         .map(Mappers::fromEventEntity)
                         .collect(Collectors.toList());
    }

    public Event getEvent(int eventId) {
        return Mappers.fromEventEntity(repository.getEvent(eventId));
    }

    public void deleteEvent(int id) {
        repository.deleteEvent(id);
    }
}
