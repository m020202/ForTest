package com.example.demo.service;

import com.example.demo.domain.animal.Animal;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalService {

    @Qualifier("dog")
    private final Animal animal;

    public void animalSound() {
        animal.sound();
    }
}
