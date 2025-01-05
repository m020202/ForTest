package com.example.demo.alarm.controller;

import com.example.demo.alarm.domain.Schedule;
import com.example.demo.alarm.dto.ScheduleRequestDTO;
import com.example.demo.alarm.service.ScheduleService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedule/create")
    public String create(@RequestBody ScheduleRequestDTO.create dto) {
        scheduleService.create(dto);

        return "OK!";
    }

    @GetMapping("/schedules")
    public List<Schedule> getSchedules() {
        List<Schedule> schedules = scheduleService.findByDayAndTime(DayOfWeek.MONDAY, LocalTime.of(16, 0, 0));
        return schedules;
    }
}
