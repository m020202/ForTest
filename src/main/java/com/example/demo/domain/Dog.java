package com.example.demo.domain;

public class Dog implements Animal {
    @Override
    public void sound() {
        System.out.println("Bark");
    }
}
