package com.trkgrn.studentinformationsystem.api.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDto {
    private Long studentId;
    private UserDto user;
    private String studentNo;
}
