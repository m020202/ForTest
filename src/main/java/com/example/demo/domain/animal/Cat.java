package com.example.demo.domain.animal;

import com.example.demo.domain.animal.Animal;

public class Cat implements Animal {
    @Override
    public void sound() {
        System.out.println("Meow");
    }
}
