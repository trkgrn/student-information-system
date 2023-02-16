package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.dto.UserDto;
import com.trkgrn.studentinformationsystem.api.model.entity.User;
import com.trkgrn.studentinformationsystem.api.service.AuthService;
import com.trkgrn.studentinformationsystem.config.security.jwt.model.AuthRequest;
import com.trkgrn.studentinformationsystem.config.security.jwt.model.AuthResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final HttpServletRequest request;
    private final AuthService authService;
    private final ModelMapper modelMapper;

    public AuthController(HttpServletRequest request, AuthService authService, ModelMapper modelMapper) {
        this.request = request;
        this.authService = authService;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) throws Exception {
        return new ResponseEntity<AuthResponse>(authService.login(authRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody User user) {
        return new ResponseEntity<UserDto>(modelMapper.map(authService.register(user), UserDto.class), HttpStatus.CREATED);
    }

    @GetMapping("/logout")
    public void logout() {
        authService.logout();
    }
}
