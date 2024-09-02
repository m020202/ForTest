package com.example.demo.domain.animal;

public class AnimalFactory {
    public static Animal createAnimal(String type) {
        if (type.equals("dog")){
            return new Dog();
        } else if (type.equals("cat")) {
            return new Cat();
        }
        throw new IllegalStateException("Unknown animal type");
    }
}
