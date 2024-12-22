package com.example.demo.service;

import com.example.demo.converter.MemberConverter;
import com.example.demo.domain.Member;
import com.example.demo.dto.JoinDTO;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void join(JoinDTO joinDTO) {
        if (isExist(joinDTO.getName())) {
            return;
        }

        String password = bCryptPasswordEncoder.encode(joinDTO.getPassword());
        joinDTO.setPassword(password);

        Member member = MemberConverter.toMember(joinDTO);
        memberRepository.save(member);
    }

    public Boolean isExist(String name) {
        return memberRepository.existsByName(name);
    }
}
