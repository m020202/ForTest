package com.example.demo.domain.animal;

import com.example.demo.domain.animal.Animal;

public class Dog implements Animal {
    @Override
    public String sound() {
        return "Bark";
    }
}
