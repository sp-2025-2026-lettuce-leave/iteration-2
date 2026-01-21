package com.uni.mapsapitest.service;

import com.uni.mapsapitest.models.User;
import com.uni.mapsapitest.models.UserRole;
import com.uni.mapsapitest.repository.UserRepository;

public class AuthService {

    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String username, String password) {
        User user = new User(username, password, UserRole.USER);
        userRepository.save(user);
        return user;
    }

    public User registerSponsor(String username, String password) {
        User sponsor = new User(username, password, UserRole.SPONSOR);
        userRepository.save(sponsor);
        return sponsor;
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }
}
