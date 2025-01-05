package com.example.demo.alarm.repository;

import com.example.demo.alarm.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
//    @Query("")
//    public List<Schedule> findByDayAndTime
}
