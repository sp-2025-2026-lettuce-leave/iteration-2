package com.uni.mapsapitest.service;

import com.uni.mapsapitest.models.Event;
import com.uni.mapsapitest.models.User;
import com.uni.mapsapitest.models.UserRole;
import com.uni.mapsapitest.repository.EventRepository;

public class EventService {

    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(User sponsor, String name, String code) {
        if (sponsor.getRole() != UserRole.SPONSOR) {
            throw new IllegalStateException("Only sponsors can create events");
        }

        Event event = new Event(name, code);
        eventRepository.save(event);
        return event;
    }

    public boolean enterEventCode(User user, String code) {
        if (user.getRole() != UserRole.USER) {
            return false;
        }

        Event event = eventRepository.findByCode(code);
        if (event != null) {
            user.addPoints(10);
            return true;
        }
        return false;
    }
}
