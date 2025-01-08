package com.example.demo.alarm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class FcmTokenRequestDTO {
    @Builder
    @Getter
    @Setter
    public static class PostTokenReq {
        String token;
    }
}
