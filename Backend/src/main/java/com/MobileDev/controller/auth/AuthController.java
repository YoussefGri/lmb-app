package com.MobileDev.controller.auth;

import com.MobileDev.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        Map<String, Object> response = authService.registerUser(user);
        return ResponseEntity.status((Integer) response.get("code")).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, Object> loginRequest) {
        String username = (String) loginRequest.get("username");
        String password = (String) loginRequest.get("password");
        int passwordTypeValue = loginRequest.get("password_type") != null ? (Integer) loginRequest.get("password_type") : 0;
        String codeApplication = (String) loginRequest.get("code_application");
        String codeVersion = (String) loginRequest.get("code_version");

        Map<String, Object> response = authService.authenticateUser(username, password, passwordTypeValue, codeApplication, codeVersion);
        return ResponseEntity.status((Integer) response.get("code")).body(response);
    }
}
