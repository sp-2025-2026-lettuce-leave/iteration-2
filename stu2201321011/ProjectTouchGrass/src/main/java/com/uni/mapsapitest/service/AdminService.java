package com.uni.mapsapitest.service;

import com.uni.mapsapitest.models.Event;
import com.uni.mapsapitest.models.User;
import com.uni.mapsapitest.models.UserRole;
import com.uni.mapsapitest.repository.EventRepository;
import com.uni.mapsapitest.repository.UserRepository;

public class AdminService {

    private UserRepository userRepository;
    private EventRepository eventRepository;

    public AdminService(UserRepository userRepository,
                        EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public void deleteEvent(User admin, Event event) {
        if (admin.getRole() != UserRole.ADMIN) {
            throw new IllegalStateException("Only admin can delete events");
        }
        eventRepository.delete(event);
    }

    public void addUser(User admin, User user) {
        if (admin.getRole() != UserRole.ADMIN) {
            throw new IllegalStateException("Only admin can manage users");
        }
        userRepository.save(user);
    }
}
