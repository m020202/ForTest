package com.example.demo.alarm.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RepeatDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DayOfWeek day;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
