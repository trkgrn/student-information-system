package com.trkgrn.studentinformationsystem.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherDto {
    private Long teacherId;
    private UserDto user;
}
