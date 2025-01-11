package com.EventsMaker.v1.repositories;

import com.EventsMaker.v1.repositories.entities.EventEntity;

import java.math.BigDecimal;
import java.util.List;

public interface EventsRepository
{
	EventEntity createEvent(String title, String description, int authorId, BigDecimal price);
	EventEntity addBookedEvent(int userId, int eventId);

	List<EventEntity> getAllEvents();

	List<EventEntity> listOwnEvents(int userId);
	List<EventEntity> listBookedEvents(int userId);
	EventEntity getEvent(int eventId);
	void deleteEvent(int id);
}
