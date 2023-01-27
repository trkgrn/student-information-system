package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.User;
import com.trkgrn.studentinformationsystem.api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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





}
