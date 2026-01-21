package com.uni.mapsapitest.repository;

import java.util.ArrayList;
import java.util.List;

import com.uni.mapsapitest.models.Event;

public class EventRepository {
    private List<Event> events = new ArrayList<>();

    public void save(Event event) {
        events.add(event);
    }

    public Event findByCode(String code) {
        for (Event event : events) {
            if (event.getEventCode().equals(code)) {
                return event;
            }
        }
        return null;
    }

    public List<Event> findAll() {
        return events;
    }

    public void delete(Event event) {
        events.remove(event);
    }
}
