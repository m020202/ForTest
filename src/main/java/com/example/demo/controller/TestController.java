package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/check")
    public String getCheck() {
        return "정상 작동합니다!!!";
    }
}
