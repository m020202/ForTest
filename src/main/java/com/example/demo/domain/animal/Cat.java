package com.example.demo.domain.animal;

import com.example.demo.domain.animal.Animal;

public class Cat implements Animal {
    @Override
    public String sound() {
        return "Meow";
    }
}
