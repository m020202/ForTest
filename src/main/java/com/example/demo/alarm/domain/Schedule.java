package com.example.demo.alarm.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //    @ElementCollection(targetClass = DayOfWeek.class)
//    @CollectionTable(name = "schedule_repeat_day", joinColumns = @JoinColumn(name = "schedule_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<DayOfWeek> repeatDays;
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RepeatDay> repeatDayList;
    private LocalTime time; // 반복 시간
}
