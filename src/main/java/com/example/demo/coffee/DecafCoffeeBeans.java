package com.example.demo.coffee;

public class DecafCoffeeBeans implements CoffeeBeans {
    @Override
    public void grind() {
        System.out.println("디카페인 원두로 갈고 있습니다.");
    }
}
