package com.uni.mapsapitest.models;

import java.util.UUID;

public class Sponsor {
    private final String id;
    private String name;

    public Sponsor(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
