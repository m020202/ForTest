package com.example.demo.config;

import com.example.demo.coffee.CoffeeBeans;
import com.example.demo.coffee.DecafCoffeeBeans;
import com.example.demo.coffee.RegularCoffeeBeans;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Bean
    public CoffeeBeans coffeeBeans() {
        return new RegularCoffeeBeans();
    }
}
