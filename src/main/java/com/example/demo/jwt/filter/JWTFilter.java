package com.example.demo.jwt.filter;

import com.example.demo.dto.member.MemberDTO;
import com.example.demo.jwt.util.CustomUserDetails;
import com.example.demo.jwt.util.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (isValidated(authorization) == false) {
            filterChain.doFilter(request, response);
            return ;
        }

        String token = authorization.split(" ")[1];

        if (!isInRedis(jwtUtil.getName(token), authorization)) {
            filterChain.doFilter(request, response);
            System.out.println("Redis에 없음!!");
            return;
        }

        try {
            jwtUtil.isExpired(token);
        } catch (ExpiredJwtException e) {
            PrintWriter writer = response.getWriter();
            writer.print("access token이 만료되었습니다.");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (!isAccess(token)) {
            PrintWriter writer = response.getWriter();
            writer.print("유효하지 않은 access token 입니다.");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        MemberDTO.forUserDetails memberDto = createMemberDtoByToken(token);

        CustomUserDetails customUserDetails = new CustomUserDetails(memberDto);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

    private Boolean isAccess(String token) {
        if (jwtUtil.getCategory(token).equals("access")) {
            return true;
        }
        return false;
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

    private Boolean isInRedis(String name, String token) {
        String value = (String) redisTemplate.opsForValue().get(name);

        if (value.equals(token)) {
            return true;
        }

        return false;
    }

    private Boolean isExpired(String token) {
        if (jwtUtil.isExpired(token)) {
            return true;
        }

        return false;
    }
}
