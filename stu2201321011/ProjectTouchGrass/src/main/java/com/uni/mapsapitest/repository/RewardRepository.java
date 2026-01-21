package com.uni.mapsapitest.repository;

import java.util.ArrayList;
import java.util.List;

import com.uni.mapsapitest.models.Reward;

public class RewardRepository {
    private List<Reward> rewards = new ArrayList<>();

    public void save(Reward reward) {
        rewards.add(reward);
    }

    public List<Reward> findAll() {
        return rewards;
    }
}
