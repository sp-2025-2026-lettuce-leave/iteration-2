package com.uni.mapsapitest.service;

import java.util.List;

import com.uni.mapsapitest.models.Reward;
import com.uni.mapsapitest.models.User;
import com.uni.mapsapitest.models.UserRole;
import com.uni.mapsapitest.repository.RewardRepository;

public class RewardService {

    private RewardRepository rewardRepository;

    public RewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    public List<Reward> viewRewards(User user) {
        if (user.getRole() != UserRole.USER) {
            throw new IllegalStateException("Only users can view rewards");
        }
        return rewardRepository.findAll();
    }

    public boolean redeemReward(User user, Reward reward) {
        if (user.getRole() != UserRole.USER) {
            return false;
        }

        if (user.getPoints() >= reward.getCost()) {
            user.deductPoints(reward.getCost());
            return true;
        }
        return false;
    }
}
