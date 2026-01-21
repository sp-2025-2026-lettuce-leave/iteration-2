package com.uni.mapsapitest.models;

import java.util.UUID;

public class Event {
    private final String id;
    private String name;
    private String eventCode;

    public Event(String name, String eventCode) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.eventCode = eventCode;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEventCode() {
        return eventCode;
    }
}