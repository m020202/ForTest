package com.example.demo.service;

import com.example.demo.domain.animal.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalService {
    @Qualifier("dog")
    private Animal animal;

    public void animalSound() {
        animal.sound();
    }

}
