package com.example.demo.coffee;

import org.springframework.stereotype.Component;

@Component
public class RegularCoffeeBeans implements CoffeeBeans {
    @Override
    public void grind() {
        System.out.println("기본 원두로 갈고 있습니다.");
    }
}
