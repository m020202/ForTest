package com.example.demo.service;

import com.example.demo.domain.animal.Animal;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    private final Animal animal;

    public AnimalService(@Qualifier("dog") Animal animal) {
        this.animal = animal;
    }

    public String animalSound() {
        return animal.sound();
    }
}
