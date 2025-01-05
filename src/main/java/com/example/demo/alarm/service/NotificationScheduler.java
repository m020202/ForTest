package com.example.demo.alarm.service;

import com.example.demo.alarm.domain.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationScheduler {
    private final ScheduleService scheduleService;

    @Scheduled(cron = "0 * * * * *")
    public void checkAndSendNotifications() {
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek today = now.getDayOfWeek();
        LocalTime currentTime = now.toLocalTime().withSecond(0).withNano(0);
        List<Schedule> schedules = scheduleService.findByDayAndTime(today, currentTime);

        for (Schedule schedule : schedules) {
            System.out.println("현재 일정: "+ schedule.getName());
        }
    }
}
