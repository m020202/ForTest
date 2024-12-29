package com.example.demo.jwt.service;

import com.example.demo.converter.MemberConverter;
import com.example.demo.domain.Member;
import com.example.demo.jwt.dto.JoinDTO;
import com.example.demo.jwt.util.JWTUtil;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTUtil jwtUtil;
    private final RedisTemplate redisTemplate;

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

    @Transactional
    public void logout(String token) {
        String name = jwtUtil.getName(token);
        if (redisTemplate.opsForValue().get(name) == null) {
            return;
        }
        redisTemplate.delete(name);
    }
}
