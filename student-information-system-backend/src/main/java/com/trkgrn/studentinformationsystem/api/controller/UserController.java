package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.dto.UserDto;
import com.trkgrn.studentinformationsystem.api.model.entity.User;
import com.trkgrn.studentinformationsystem.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<User> add(User user){
        User addedUser = userService.add(user);
        return ResponseEntity.ok(addedUser);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> list(){
        return ResponseEntity.ok(userService.list());
    }

    @GetMapping("/findByJwt")
    public ResponseEntity<UserDto> findByJwt(){
        return ResponseEntity.ok(modelMapper.map(userService.findByJwt(), UserDto.class));
    }
}
