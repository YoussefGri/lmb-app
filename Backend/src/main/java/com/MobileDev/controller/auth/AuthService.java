package com.MobileDev.controller.auth;


import com.MobileDev.model.Password_Type;
import com.MobileDev.model.User;
import com.MobileDev.repository.UserRepository;
import com.MobileDev.security.PasswordEncoder;
import com.MobileDev.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtUtil;

    public Map<String, Object> registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseUtil.generateResponse(400, "Username is already taken", null);
        }

        user.setPassword(passwordEncoder.encoder().encode(user.getPassword()));
        userRepository.save(user);

        return ResponseUtil.generateResponse(200, "User registered successfully", null);
    }

    public Map<String, Object> authenticateUser(String username, String password, int passwordTypeValue, String codeApplication, String codeVersion) {
        if (username == null || password == null || codeApplication == null || codeVersion == null) {
            return ResponseUtil.generateResponse(400, "Missing required parameters", null);
        }

        Password_Type passwordType;
        try {
            passwordType = Password_Type.fromValue(passwordTypeValue);
        } catch (IllegalArgumentException e) {
            return ResponseUtil.generateResponse(400, "Invalid password_type", null);
        }

        User user = (User) userRepository.findByUsername(username);
        if (user == null) {
            return ResponseUtil.generateResponse(401, "Login/Mot de passe incorrects", null);
        }

        if (passwordType == Password_Type.PLAIN_TEXT) {
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                return ResponseUtil.generateResponse(401, "Login/Mot de passe incorrects", null);
            }
        } else if (passwordType == Password_Type.HASHED) {
            if (!passwordEncoder.encoder().matches(password, user.getPassword())) {
                return ResponseUtil.generateResponse(401,
                        "Login/Mot de passe incorrects", null);
            }
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(username, null,
                            user.getAuthorities()));
        }

        String token = jwtUtil.generateToken((UserDetails) user);

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);

        return ResponseUtil.generateResponse(200, "", data);
    }
}
