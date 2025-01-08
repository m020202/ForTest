package com.example.demo.alarm.service;

import com.example.demo.alarm.domain.User;
import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import com.google.cloud.BaseServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FCMService {
    private final UserService userService;
    @Transactional
    public void setToken(Long id, String token) throws BaseServiceException {
        User user = userService.findById(id);
        user.setFcmToken(token);
    }
}
