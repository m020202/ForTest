package com.example.demo.domain;

import lombok.Getter;

@Getter
public class Person {
    private String name;
    private Integer age;

    private Person (String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    // 정적 팩토리 메서드

    // 이름만 가지는 객체 생성
    public static Person ofName(String name) {
        return new Person(name, 0);
    }

    // 나이만 가지는 객체 생성
    public static Person ofAge(Integer age) {
        return new Person("", age);
    }
}
