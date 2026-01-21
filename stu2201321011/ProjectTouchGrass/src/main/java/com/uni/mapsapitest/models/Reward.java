package com.uni.mapsapitest.models;

import java.util.UUID;

public class Reward {
    private final String id;
    private String name;
    private int cost;

    public Reward(String name, int cost) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}
