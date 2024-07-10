package com.MobileDev.controller.auth;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String username;
    private String password;
    private int passwordType = 0; // Default to clear text password
    private String codeApplication = "webservice_externe";
    private String codeVersion = "1";
}
