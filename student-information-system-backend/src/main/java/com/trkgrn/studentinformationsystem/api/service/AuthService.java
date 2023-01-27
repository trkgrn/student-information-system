package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.exception.SQLExc;
import com.trkgrn.studentinformationsystem.api.model.entity.Token;
import com.trkgrn.studentinformationsystem.api.model.entity.User;
import com.trkgrn.studentinformationsystem.config.security.jwt.model.AuthRequest;
import com.trkgrn.studentinformationsystem.config.security.jwt.model.AuthResponse;
import com.trkgrn.studentinformationsystem.config.security.jwt.service.JwtUtil;
import com.trkgrn.studentinformationsystem.config.security.userdetail.CustomUserDetails;
import com.trkgrn.studentinformationsystem.config.security.userdetail.UserDetailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final UserDetailService userDetailsService;

    private final UserService userService;

    private final TokenService tokenService;



    @Value("${jwt.login.expire.hours}")
    private Long expireHours;

    public AuthService(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserDetailService userDetailsService, UserService userService, TokenService tokenService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.tokenService = tokenService;

    }


    public AuthResponse login(AuthRequest authRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getTckNo(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorret username or password", ex);
        }
        final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getTckNo());
        final String jwt = jwtUtil.generateToken(userDetails, expireHours);

        // redise kaydet
        tokenService.save(new Token(authRequest.getTckNo(), jwt), expireHours);


        return new AuthResponse(jwt, userDetails.getRole().getName(), userDetails.getUser().getUserId());
    }

    public String register(User user) {
        try {
            this.userService.add(user);
        } catch (DataIntegrityViolationException ex) {
            throw new SQLExc("Sistemde bu bilgilere ait kayıt bulunmaktadır. Lütfen bilgilerinizi kontrol edin.");
        }

        return "Kayıt başarılı";
    }

}
