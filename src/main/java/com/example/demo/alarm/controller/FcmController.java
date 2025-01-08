package com.example.demo.alarm.controller;

import com.example.demo.alarm.dto.FcmTokenRequestDTO;
import com.example.demo.alarm.service.FCMService;
import com.google.cloud.BaseServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FcmController {
    private final FCMService fcmService;

    @PostMapping("{id}/token")
    public String setToken(@PathVariable("id") Long id, @RequestBody FcmTokenRequestDTO.PostTokenReq request) {
        try {
            fcmService.setToken(id, request.getToken());
            return "OK!";
        } catch (BaseServiceException e) {
            return e.getReason();
        }
    }
}
