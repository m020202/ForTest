package com.example.demo.alarm.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

public class ScheduleRequestDTO {
    @Builder
    @Getter
    @Setter
    public static class create {
        private String name;
        private List<String> repeatDays;
        private LocalTime time;
    }
}
