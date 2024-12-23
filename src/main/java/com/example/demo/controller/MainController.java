package com.example.demo.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class MainController {

    @GetMapping("/")
    public String mainP() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority();
        return name + "님 (" + role + ") 환영합니다!";
    }

}
