package com.example.demo.jwt.controller;

import com.example.demo.jwt.dto.JoinDTO;
import com.example.demo.jwt.service.LoginService;
import com.example.demo.jwt.util.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final JWTUtil jwtUtil;
    private final RedisTemplate redisTemplate;

    @PostMapping("/join")
    public String join(@RequestBody JoinDTO joinDTO) {
        loginService.join(joinDTO);
        return "회원가입 성공!";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        loginService.logout(token);

        return "로그아웃 성공!";
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        String refresh = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("Refresh")) {
                refresh = cookie.getValue();
            }
        }

        if (refresh == null) {
            return new ResponseEntity<>("Refresh Token이 없습니다", HttpStatus.BAD_REQUEST);
        }

        // Refresh 토큰이 redis에 저장된 값과 동일한지 체크
        if (!refresh.equals(redisTemplate.opsForValue().get("Refresh:" + jwtUtil.getName(refresh)))) {
            return new ResponseEntity<>("Refresh Token이 유효하지 않습니다", HttpStatus.BAD_REQUEST);
        }

        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>("Refresh Token이 만료되었습니다.", HttpStatus.BAD_REQUEST);
        }

        if (!isRefresh(refresh)) {
            return new ResponseEntity<>("유효하지 않은 Refresh Token 입니다.", HttpStatus.BAD_REQUEST);
        }
        List<String> newTokens = createNewAccessToken(refresh);
        String newAccessToken = newTokens.get(0);
        String newRefreshToken = newTokens.get(1);

        response.setHeader("Authorization", newAccessToken);
        response.addCookie(createCookie("Refresh", newRefreshToken));
        redisTemplate.opsForValue().set("Access:" + jwtUtil.getName(refresh), newAccessToken);
        redisTemplate.opsForValue().set("Refresh:" + jwtUtil.getName(refresh), newRefreshToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Boolean isRefresh(String refresh) {
        String category = jwtUtil.getCategory(refresh);
        if (!category.equals("refresh")) {
            return false;
        }

        return true;
    }

    private List<String> createNewAccessToken(String refresh) {
        String name = jwtUtil.getName(refresh);
        String role = jwtUtil.getRole(refresh);

        String accessToken = jwtUtil.createJwt("access", name, role, 600000L);
        String refreshToken = jwtUtil.createJwt("refresh", name, role, 86400000L);
        List<String> list = new ArrayList<>();
        list.add(accessToken);
        list.add(refreshToken);

        return list;
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setHttpOnly(true);

        return cookie;
    }
}


