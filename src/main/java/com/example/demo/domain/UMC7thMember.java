package com.example.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
@Getter
public class UMC7thMember {
    private int age;
    private String nickname;
    private String school;
    private String studyPart;

    public UMC7thMember(int age, String nickname, String school, String studyPart) {
        this.age = age;
        this.nickname = nickname;
        this.school = school;
        this.studyPart = studyPart;
    }
}
