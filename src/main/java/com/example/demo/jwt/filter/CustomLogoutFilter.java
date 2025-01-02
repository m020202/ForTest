package com.example.demo.jwt.filter;

import com.example.demo.jwt.util.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomLogoutFilter extends GenericFilterBean {
    private final JWTUtil jwtUtil;
    private final RedisTemplate redisTemplate;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String requestUri = httpServletRequest.getRequestURI();
        if (!requestUri.matches("/logout$")) {
            chain.doFilter(request, response);
            return;
        }

        if (!httpServletRequest.getMethod().equals("POST")) {
            chain.doFilter(request, response);
            return;
        }

        deleteAccess(httpServletRequest, httpServletResponse);
        deleteRefresh(httpServletRequest, httpServletResponse);
    }

    private void deleteAccess(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");

        if (redisTemplate.opsForValue().get("Access:" + jwtUtil.getName(token)).equals(token)) {
            redisTemplate.delete("Access:" + jwtUtil.getName(token));
        }
    }

    private void deleteRefresh(HttpServletRequest request, HttpServletResponse response) {
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Refresh")) {
                refresh = cookie.getValue();
                break;
            }
        }

        if (refresh == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String category = jwtUtil.getCategory(refresh);
        if (!category.equals("refresh")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Redis 에 저장된 Refresh 토큰 삭제
        if (redisTemplate.opsForValue().get("Refresh:" +jwtUtil.getName(refresh)).equals(refresh)) {
            redisTemplate.delete("Refresh:" + jwtUtil.getName(refresh));
        }

        // 쿠키 초기화
        Cookie cookie = new Cookie("refresh", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
