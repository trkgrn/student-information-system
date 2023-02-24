package com.trkgrn.studentinformationsystem.api.model.dto;

import com.trkgrn.studentinformationsystem.api.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private Long userId;

    private String firstName;

    private String lastName;

    private String tckNo;

    private String telNo;

    private String email;

    private String address;

    private Role role;
}
