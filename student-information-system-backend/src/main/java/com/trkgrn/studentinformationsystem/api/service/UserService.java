package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.exception.ExpiredJwtExc;
import com.trkgrn.studentinformationsystem.api.model.entity.User;
import com.trkgrn.studentinformationsystem.api.repository.UserRepository;
import com.trkgrn.studentinformationsystem.config.security.jwt.service.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User updateUser(User user, Long id){
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isPresent()){
            User updatedUser = userOptional.get();
            updatedUser.setUserId(id);
            updatedUser.setTckNo(user.getTckNo());
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setAddress(user.getAddress());
            updatedUser.setTelNo(user.getTelNo());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            updatedUser.setRole(user.getRole());
            return this.userRepository.save(updatedUser);
        }
        return null;
    }

    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
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
