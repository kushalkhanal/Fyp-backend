package org.example.gymkhanabackend.service;

import org.example.gymkhanabackend.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> getAllEvents();
    Event createEvent(Event event);
    Optional<Event> getEventById(Long id);
    Event updateEvent(Long id, Event eventDetails);
    void deleteEvent(Long id);
}
