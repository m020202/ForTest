package com.example.demo.alarm.controller;

import com.example.demo.alarm.dto.ScheduleRequestDTO;
import com.example.demo.alarm.service.ScheduleService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private ScheduleService scheduleService;

    @PostMapping("/schedule/create")
    public String create(@RequestBody ScheduleRequestDTO.create dto) {
        return "OK!";
    }
}
