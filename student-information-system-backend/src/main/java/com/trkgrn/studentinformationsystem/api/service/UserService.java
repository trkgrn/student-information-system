package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.exception.ExpiredJwtExc;
import com.trkgrn.studentinformationsystem.api.model.entity.User;
import com.trkgrn.studentinformationsystem.api.repository.UserRepository;
import com.trkgrn.studentinformationsystem.config.security.jwt.service.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final HttpServletRequest request;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.request = request;
    }

    public User add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User addedUser = this.userRepository.save(user);
        return addedUser;
    }

    public List<User> list(){
        return this.userRepository.findAll();
    }

    public User findByTCKNo(String tckNo) {
        return this.userRepository.findByTckNo(tckNo);
    }

    public User findByJwt() {
        if (request.getHeader("Authorization") == null) {
            throw new ExpiredJwtExc("Jwt Not Found");
        }
        else {
            String jwt = request.getHeader("Authorization").substring(7);
            String tckNo = jwtUtil.extractUsername(jwt);
            return this.userRepository.findByTckNo(tckNo);
        }
    }





}
