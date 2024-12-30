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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        String token = request.getHeader("Authorization").split(" ")[1];
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

        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>("Refresh Token이 만료되었습니다.", HttpStatus.BAD_REQUEST);
        }

        if (!isRefresh(refresh)) {
            return new ResponseEntity<>("유효하지 않은 Refresh Token 입니다.", HttpStatus.BAD_REQUEST);
        }

        String newAccessToken = createNewAccessToken(refresh);

        response.setHeader("Authorization", newAccessToken);
        redisTemplate.opsForValue().set(jwtUtil.getName(refresh), newAccessToken);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Boolean isRefresh(String refresh) {
        String category = jwtUtil.getCategory(refresh);
        if (!category.equals("refresh")) {
            return false;
        }

        return true;
    }

    private String createNewAccessToken(String refresh) {
        String name = jwtUtil.getName(refresh);
        String role = jwtUtil.getRole(refresh);

        return jwtUtil.createJwt("access", name, role, 600000L);
    }
}


