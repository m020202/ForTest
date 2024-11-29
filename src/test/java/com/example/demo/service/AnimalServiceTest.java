package com.example.demo.service;

import com.example.demo.domain.animal.Animal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

    @Mock
    @Qualifier("dog")
    private Animal mockAnimal;
    @InjectMocks
    private AnimalService animalService;

    @Test
    void testAnimalSound() {
       String sound = animalService.animalSound();

        Assertions.assertThat(sound).isEqualTo(mockAnimal.sound());
    }

}