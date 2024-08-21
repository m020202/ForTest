package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Operation(summary = "user name 을 입력 받고 API가 잘 오는지 테스트", description = "user name을 쿼리스트링으로 주세요 !!")
    @GetMapping("/check")
    public String getCheck(@Parameter(description = "Name of User") @RequestParam String name) {
        return "정상 작동합니다!!!";
    }
}
