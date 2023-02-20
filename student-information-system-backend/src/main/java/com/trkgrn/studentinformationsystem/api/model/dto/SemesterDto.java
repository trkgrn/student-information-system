package com.trkgrn.studentinformationsystem.api.model.dto;

import com.trkgrn.studentinformationsystem.api.model.entity.EducationSeason;
import com.trkgrn.studentinformationsystem.api.model.entity.Period;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SemesterDto {
    private EducationSeason educationSeason;
    private Period period;
}
