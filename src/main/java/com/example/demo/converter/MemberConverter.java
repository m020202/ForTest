package com.example.demo.converter;

import com.example.demo.domain.Member;
import com.example.demo.dto.JoinDTO;
import com.example.demo.dto.member.MemberDTO;

public class MemberConverter {
    public static Member toMember(JoinDTO joinDTO) {
        return Member.builder()
                .name(joinDTO.getName())
                .password(joinDTO.getPassword())
                .role("ROLE_ADMIN")
                .build();
    }

    public static MemberDTO.forUserDetails forUserDetails(Member member) {
        return MemberDTO.forUserDetails.builder()
                .name(member.getName())
                .password(member.getPassword())
                .role(member.getRole())
                .build();
    }
}
