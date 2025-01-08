package com.example.demo.alarm.service;

import com.example.demo.alarm.domain.User;
import com.example.demo.alarm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
