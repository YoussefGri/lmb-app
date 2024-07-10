package com.MobileDev.controller.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthResponse {
    private int code;
    private String message;
    private AuthData datas;
    private List<String> warnings;

    @Getter
    @Setter
    public static class AuthData {
        private String token;

    }
}
