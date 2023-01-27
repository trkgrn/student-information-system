package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.entity.User;
import com.trkgrn.studentinformationsystem.api.service.AuthService;
import com.trkgrn.studentinformationsystem.api.service.UserService;
import com.trkgrn.studentinformationsystem.config.security.jwt.model.AuthRequest;
import com.trkgrn.studentinformationsystem.config.security.jwt.model.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final HttpServletRequest request;
    private final AuthService authService;

    public AuthController(UserService userService, HttpServletRequest request, AuthService authService) {
        this.userService = userService;
        this.request = request;
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) throws Exception {
        return new ResponseEntity<AuthResponse>(authService.login(authRequest), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        return new ResponseEntity<>(authService.register(user), HttpStatus.CREATED);
    }
}
