package com.example.demo.config;

import com.example.demo.coffee.CoffeeBeans;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BeansConfiguration.class)
public class MachineConfiguration {
    @Bean
    public MachineConfiguration machineConfiguration(CoffeeBeans coffeeBeans) {
        return new MachineConfiguration();
    }
}
