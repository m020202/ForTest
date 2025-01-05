package com.example.demo.alarm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class NotificationScheduler {
    private final ScheduleService scheduleService;

    @Scheduled(fixedRate = 60000)
    public void checkAndSendNotifications() {
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
    }
}
