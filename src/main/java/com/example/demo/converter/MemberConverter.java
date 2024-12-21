package com.example.demo.converter;

import com.example.demo.domain.Member;
import com.example.demo.dto.JoinDTO;

public class MemberConverter {
    public static Member toMember(JoinDTO joinDTO) {
        return Member.builder()
                .name(joinDTO.getUsername())
                .password(joinDTO.getPassword())
                .role("ROLE_ADMIN")
                .build();
    }
}
