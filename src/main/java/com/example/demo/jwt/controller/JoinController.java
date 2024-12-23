package com.example.demo.jwt.controller;

import com.example.demo.jwt.dto.JoinDTO;
import com.example.demo.jwt.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;

    @PostMapping("/join")
    public String join(@RequestBody JoinDTO joinDTO) {
        joinService.join(joinDTO);
        return "회원가입 성공!";
    }
}
