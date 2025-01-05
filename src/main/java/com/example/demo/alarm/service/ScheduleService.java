package com.example.demo.alarm.service;

import com.example.demo.alarm.domain.Schedule;
import com.example.demo.alarm.dto.ScheduleRequestDTO;
import com.example.demo.alarm.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void create(ScheduleRequestDTO.create dto) {
        Set<DayOfWeek> repeatDays = new HashSet<>();
        for (String d : dto.getRepeatDays()) {
            switch (d) {
                case "MONDAY":
                    repeatDays.add(DayOfWeek.MONDAY);
                    break;
                case "TUESDAY":
                    repeatDays.add(DayOfWeek.TUESDAY);
                    break;
                case "WEDNESDAY":
                    repeatDays.add(DayOfWeek.WEDNESDAY);
                    break;
                case "THURSDAY":
                    repeatDays.add(DayOfWeek.THURSDAY);
                    break;
                case "FRIDAY":
                    repeatDays.add(DayOfWeek.FRIDAY);
                    break;
                case "SATURDAY":
                    repeatDays.add(DayOfWeek.SATURDAY);
                    break;
                default:
                    repeatDays.add(DayOfWeek.SUNDAY);
                    break;
            }
        }
        Schedule schedule = Schedule.builder()
                .name(dto.getName())
                .repeatDays(repeatDays)
                .time(LocalTime.parse(dto.getTime()))
                .build();

        scheduleRepository.save(schedule);
    }

    public List<Schedule> findByDayAndTime(DayOfWeek dayOfWeek, LocalTime time) {
        return scheduleRepository.findByDayAndTime(dayOfWeek.name(), time);
    }
}
