package com.craapp.craapp.controller;


import com.craapp.craapp.dto.LoginDto;
import com.craapp.craapp.repositories.AppuserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final AppuserRepository appuserRepository;

    private final PasswordEncoder passwordEncoder;

    public LoginController(AuthenticationManager authenticationManager, AppuserRepository appuserRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.appuserRepository = appuserRepository;
        this.passwordEncoder = passwordEncoder;
    }


   /* @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestParam("Authorization") String authorizationHeader,  LoginDto loginDto) {
        byte[] base64Credentials = authorizationHeader.substring("Basic ".length()).getBytes();
        byte[] credentials = Base64.getDecoder().decode(base64Credentials);
        String usernameAndPassword = new String(credentials);
        String[] values = usernameAndPassword.split(":", 2);
        if (values.length != 2) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        String username = values[0];
        String password = values[1];

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        authentication = authenticationManager.authenticate(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok("User signed-in successfully");
    }*/
    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> authenticateUser(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authorizationHeader,
                                                                @RequestBody(required = false) LoginDto loginDto) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Basic ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        byte[] base64Credentials = authorizationHeader.substring("Basic ".length()).getBytes();
        byte[] credentials = Base64.getDecoder().decode(base64Credentials);
        String usernameAndPassword = new String(credentials);
        String[] values = usernameAndPassword.split(":", 2);
        if (values.length != 2) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = values[0];
        String password = values[1];

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        authentication = authenticationManager.authenticate(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok().body(Map.of("message", "User signed-in successfully"));
    }
}
