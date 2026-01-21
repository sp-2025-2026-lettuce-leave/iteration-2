package com.uni.mapsapitest.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.uni.mapsapitest.models.User;

public class UserRepository {
    private Map<String, User> usersByUsername = new HashMap<>();

    public void save(User user) {
        usersByUsername.put(user.getUsername(), user);
    }

    public User findByUsername(String username) {
        return usersByUsername.get(username);
    }

    public Collection<User> findAll() {
        return usersByUsername.values();
    }
}
