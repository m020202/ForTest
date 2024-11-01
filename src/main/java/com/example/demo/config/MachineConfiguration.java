package com.example.demo.config;

import com.example.demo.coffee.CoffeeBeans;
import com.example.demo.coffee.CoffeeMachine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BeansConfiguration.class)
public class MachineConfiguration {
    @Bean
    public CoffeeMachine coffeeMachine(CoffeeBeans coffeeBeans) {
        return new CoffeeMachine(coffeeBeans);
    }
}
