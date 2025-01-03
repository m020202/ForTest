package com.example.demo.jwt.service;

import com.example.demo.converter.MemberConverter;
import com.example.demo.domain.Member;
import com.example.demo.jwt.util.CustomUserDetails;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByName(username);

        if(isValidated(member)) {
            return new CustomUserDetails(MemberConverter.forUserDetails(member));
        }

        return null;
    }

    private Boolean isValidated(Member member) {

        return (member != null) ? true : false;
    }
}
