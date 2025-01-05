package com.example.demo.alarm.repository;

import com.example.demo.alarm.domain.Schedule;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value = "SELECT s.* FROM schedule s " +
            "JOIN schedule_repeat_day d ON s.id = d.schedule_id " +
            "WHERE d.repeat_days = :day AND s.time = :time",
            nativeQuery = true
    )
    List<Schedule> findByDayAndTime(@Param("day") String day, @Param("time") LocalTime time);

}
