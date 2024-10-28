package com.example.demo.coffee;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CoffeeMachine {
    private CoffeeBeans coffeeBeans;

    @Autowired
    public CoffeeMachine(CoffeeBeans coffeeBeans) {
        this.coffeeBeans = coffeeBeans;
    }


}
