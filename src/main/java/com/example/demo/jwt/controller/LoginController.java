package com.example.demo.jwt.controller;

import com.example.demo.jwt.dto.JoinDTO;
import com.example.demo.jwt.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/join")
    public String join(@RequestBody JoinDTO joinDTO) {
        loginService.join(joinDTO);
        return "회원가입 성공!";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization").split(" ")[1];
        loginService.logout(token);

        return "로그아웃 성공!";
    }
}

