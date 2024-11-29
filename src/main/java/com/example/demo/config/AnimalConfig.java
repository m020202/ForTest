package com.example.demo.config;

import com.example.demo.domain.animal.Animal;
import com.example.demo.domain.animal.Cat;
import com.example.demo.domain.animal.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalConfig {

    @Bean
    public Animal cat() {
        return new Cat();
    }

    @Bean
    public Animal dog() {
        return new Dog();
    }
}
