package com.example.demo.filter;

import com.example.demo.domain.Member;
import com.example.demo.dto.member.MemberDTO;
import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.jwt.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (isValidated(authorization) == false) {
            System.out.println("token null!!");
            filterChain.doFilter(request, response);

            return ;
        }

        System.out.println("authorization now");

        String token = authorization.split(" ")[1];
        if (isExpired(token)) {
            System.out.println("token이 만료되었습니다");
            filterChain.doFilter(request, response);

            return;
        }

        MemberDTO.forUserDetails memberDto = createMemberDtoByToken(token);

        CustomUserDetails customUserDetails = new CustomUserDetails(memberDto);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

    private MemberDTO.forUserDetails createMemberDtoByToken(String token) {
        return MemberDTO.forUserDetails.builder()
                .name(jwtUtil.getName(token))
                .password("temp")
                .role(jwtUtil.getRole(token))
                .build();
    }

    private Boolean isValidated(String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return false;
        }

        return true;
    }

    private Boolean isExpired(String token) {
        if (jwtUtil.isExpired(token)) {
            return true;
        }

        return false;
    }
}
