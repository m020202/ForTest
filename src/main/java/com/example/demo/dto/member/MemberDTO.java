package com.example.demo.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class MemberDTO {

    @Builder
    @Getter
    @Setter
    public static class forUserDetails {
        private String name;
        private String role;
        private String password;
    }

}
