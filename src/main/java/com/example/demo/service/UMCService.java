package com.example.demo.service;

import com.example.demo.domain.UMC7thMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UMCService {
    private final UMC7thMember umc7thMember;

    @Autowired
    public UMCService(UMC7thMember umc7thMember) {
        this.umc7thMember = umc7thMember;
    }


    public void printMemberInfo() {
        System.out.println("나이: " + umc7thMember.getAge());
        System.out.println("너디너리 닉네임: " + umc7thMember.getNickname());

    }
}
