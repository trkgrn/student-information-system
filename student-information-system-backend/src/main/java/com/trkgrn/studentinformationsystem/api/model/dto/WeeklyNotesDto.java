package com.trkgrn.studentinformationsystem.api.model.dto;

import com.trkgrn.studentinformationsystem.api.model.entity.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WeeklyNotesDto {

    private Long weeklyNotesId;
    private Integer weekNumber;
    private String description;
    private List<File> files;
}
